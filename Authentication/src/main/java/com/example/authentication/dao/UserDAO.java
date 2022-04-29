package com.example.authentication.dao;

import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> loadUserByUsername(String Username);
    List<Role> getRoleList(User user);
    Boolean checkExist(User user);
    void addUser(User user);
}
