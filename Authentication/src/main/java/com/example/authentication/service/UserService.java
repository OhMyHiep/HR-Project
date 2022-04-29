package com.example.authentication.service;

import com.example.authentication.dao.UserDAO;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService  implements UserDetailsService{

    private UserDAO userDao;

    @Autowired
    public void setUserDAO(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.loadUserByUsername(username);

        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("Username does not exist");
        }

        User user = userOptional.get();

        return AuthUserDetail.builder()
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        List<Role> roleList = userDao.getRoleList(user);
        for (Role role :  roleList){
            userAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return userAuthorities;
    }

    public Boolean checkExist(User user){
        return userDao.checkExist(user);
    }

    public void addUser(User user){
        userDao.addUser(user);
    }
}
