package com.example.employeeservice.service;

import com.example.employeeservice.dao.ContactDAO;
import com.example.employeeservice.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vivian Ma
 * @created 04/04/2022 - 7:30 PM
 */
@Service
public class ContactService {
    private ContactDAO contactDAO;

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public Contact save(Contact contact){
        return contactDAO.save(contact);
    }

    public Optional<Contact> getById(String id){
        return contactDAO.findById(id);
    }
}
