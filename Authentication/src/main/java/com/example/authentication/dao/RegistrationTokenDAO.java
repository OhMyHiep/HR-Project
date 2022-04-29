package com.example.authentication.dao;

import com.example.authentication.entity.RegistrationToken;

public interface RegistrationTokenDAO {
    void addToken(RegistrationToken token);
}
