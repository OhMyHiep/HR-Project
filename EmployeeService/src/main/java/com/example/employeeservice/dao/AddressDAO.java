package com.example.employeeservice.dao;

import com.example.employeeservice.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 8:07 PM
 */
@Repository
public interface AddressDAO extends MongoRepository<Address, String> {

}
