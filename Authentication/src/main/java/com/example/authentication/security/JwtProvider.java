package com.example.authentication.security;

import com.example.authentication.domain.message.SimpleMessage;
import com.example.authentication.entity.RegistrationToken;
import com.example.authentication.service.TokenService;
import com.example.authentication.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("JavaTraining")
    private String key;

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService){this.tokenService = tokenService;}

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String createToken(UserDetails userDetails){
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("permissions", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }


    public SimpleMessage generateToken(HttpServletRequest request, String email) throws IOException {
        String prefixedToken = request.getHeader("Authorization"); // extract
        String token = prefixedToken.substring(7);
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode
        String username = claims.getSubject();
        int MinTillExpire = 5;//for demo purpose: set this to 3 minus
        Date expirationTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(MinTillExpire));
        Claims newClaim = Jwts.claims().setSubject(username);// this should also be email, not username
        newClaim.put("expirationTime", expirationTime);
        String tokenStr = Jwts.builder()
                .setClaims(newClaim)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        RegistrationToken newToken = RegistrationToken.builder()
                .CreateBy(username)
                .Email(email) //This should be email from user - I just being lazy
                .ExpirationDate(expirationTime)
                .Token(tokenStr)
                .build();
        tokenService.addToken(newToken);


        String m = "Please use this token for registration:\nBearer "+tokenStr+"\nThe token will be expired in 5 minutes";
        rabbitTemplate.convertAndSend("directExchange", "token", email+"+++"+m);

        return SimpleMessage.builder().message(String.format("Token created for %s",email)).build();
    }



    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request){
        String prefixedToken = request.getHeader("Authorization"); // extract
//        System.out.println(prefixedToken);
        String token = prefixedToken.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode

        String username = claims.getSubject();
        List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("permissions");

        List<GrantedAuthority> authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                .collect(Collectors.toList());
        System.out.println(authorities);
        return Optional.of(AuthUserDetail.builder()
                .username(username)
                .authorities(authorities)
                .build());
    }
}
