package com.GroupProject.applicationservice.config;

import com.GroupProject.applicationservice.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/application-service/*").permitAll()
                .antMatchers("/application-service/download/*").permitAll()
                .antMatchers("/application-service/documents/upload").hasAuthority("HR")
                .antMatchers("application-service/applications/upload").permitAll()
//                .antMatchers("").hasAuthority("delete")
                .anyRequest()
                .authenticated();
    }

}
