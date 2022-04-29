package com.example.employeeservice.dao;

import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.PersonalDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 8:34 PM
 */
@Repository
public interface PersonalDocumentDAO extends MongoRepository<PersonalDocument, String> {

}
