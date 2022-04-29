package com.example.employeeservice.dao;

import com.example.employeeservice.domain.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 8:33 PM
 */
@Repository
public interface ContactDAO extends MongoRepository<Contact, String> {
}
