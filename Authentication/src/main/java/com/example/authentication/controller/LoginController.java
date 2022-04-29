package com.example.authentication.controller;

import com.example.authentication.domain.message.SimpleMessage;
import com.example.authentication.exception.UserAlreadyExistAuthenticationException;
import com.example.authentication.domain.request.LoginRequest;
import com.example.authentication.domain.response.LoginResponse;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.entity.UserRole;
import com.example.authentication.security.AuthUserDetail;
import com.example.authentication.security.JwtProvider;
import com.example.authentication.service.RoleService;
import com.example.authentication.service.UserRoleService;
import com.example.authentication.service.UserService;
import com.example.authentication.utils.HibernateUtil;
import io.jsonwebtoken.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){this.userService = userService;}


    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService){this.roleService = roleService;}

    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService){this.userRoleService = userRoleService;}


    @PostMapping("auth/login") //for login
    public LoginResponse login(@RequestBody LoginRequest request){

        Authentication authentication;

        try{
          authentication = authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
          );
        } catch (AuthenticationException e){
            throw new BadCredentialsException("Provided credential is invalid.");
        }

        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();

        String token = jwtProvider.createToken(authUserDetail);
        return LoginResponse.builder()
                .message("Welcome " + authUserDetail.getUsername())
                .token(token)
                .build();
    }

    //Generating HR account and the Roles
    @GetMapping()
    public String generateFakeData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        //default roles
        Role HR = Role.builder()
                    .RoleName("HR")
                    .RoleDescription("This role represents HR")
                    .CreateDate(new Date(System.currentTimeMillis()))
                    .LastModificationDate(new Date(System.currentTimeMillis()))
                    .build();
        Role Employee = Role.builder()
                .RoleName("Employee")
                .RoleDescription("This role represents Employee")
                .CreateDate(new Date(System.currentTimeMillis()))
                .LastModificationDate(new Date(System.currentTimeMillis()))
                .build();
        //created some users for testings
        User user1 = User.builder()
                        .username("user1")
                        .password("user1")
                        .email("user1")
                        .CreateDate(new Date(System.currentTimeMillis()))
                        .LastModificationDate(new Date(System.currentTimeMillis()))
                        .ActiveFlag(true)
                        .build();
        //Register the user with their role
        UserRole user1RoleHR= UserRole.builder()
                                .user(user1)
                                .role(HR)
                                .ActiveFlag(true)
                                .CreateDate(new Date(System.currentTimeMillis()))
                                .LastModificationDate(new Date(System.currentTimeMillis()))
                                .build();
        UserRole user1RoleEmployee= UserRole.builder()
                                .user(user1)
                                .role(Employee)
                                .ActiveFlag(true)
                                .CreateDate(new Date(System.currentTimeMillis()))
                                .LastModificationDate(new Date(System.currentTimeMillis()))
                                .build();
        session.persist(user1);
        session.persist(HR);
        session.persist(Employee);
        session.persist(user1RoleHR);
        session.persist(user1RoleEmployee);
        tx.commit();
        session.close();
        return "OK";
    }

    //generate Token only HR can access into this
    @GetMapping("/generate")
    public SimpleMessage generateToken(HttpServletRequest request, @RequestParam("email") String email) throws IOException {
        return jwtProvider.generateToken(request,email);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user, HttpServletRequest request) throws JwtException, UserAlreadyExistAuthenticationException {
        Date now = new Date(System.currentTimeMillis());
        String prefixedToken = request.getHeader("Authorization"); // extract
        String token = prefixedToken.substring(7);
        Claims claims = Jwts.parser().setSigningKey("JavaTraining").parseClaimsJws(token).getBody(); // decode
        String username = claims.getSubject();
        Date expireTime = new Date((Long) claims.get("expirationTime"));
        if(now.after(expireTime)){
            throw new JwtException("Token is expired");
        }
        if(userService.checkExist(user)){
            throw new UserAlreadyExistAuthenticationException("Account already exists");
        }
        user.setCreateDate(now);
        user.setLastModificationDate(now);
        user.setActiveFlag(true);
        userService.addUser(user);
        UserRole ur = UserRole.builder()
                .user(user)
                .ActiveFlag(true)
                .role(roleService.getRole("Employee"))
                .CreateDate(now)
                .LastModificationDate(now)
                .build();
        userRoleService.addUR(ur);
        return "Successfully created new user";
    }
}
