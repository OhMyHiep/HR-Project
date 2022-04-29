package com.example.authentication.service;


import com.example.authentication.dao.RegistrationTokenDAO;
import com.example.authentication.entity.RegistrationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenService {
    private RegistrationTokenDAO tokenDAO;

    @Autowired
    public void setTokenDAO(RegistrationTokenDAO tokenDAO){this.tokenDAO = tokenDAO;}

    public void addToken(RegistrationToken token){tokenDAO.addToken(token);}
}
