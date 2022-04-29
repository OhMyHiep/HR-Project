package com.example.authentication.service;

import com.example.authentication.dao.UserRoleDAO;
import com.example.authentication.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleService {
    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO){this.userRoleDAO = userRoleDAO;}

    public void addUR(UserRole userRole){
        userRoleDAO.addUR(userRole);
    }
}
