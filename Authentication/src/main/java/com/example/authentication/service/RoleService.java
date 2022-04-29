package com.example.authentication.service;
import com.example.authentication.dao.RoleDAO;
import com.example.authentication.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO){this.roleDAO = roleDAO;}

    public Role getRole(String role){
        return roleDAO.getRole(role);
    }
}
