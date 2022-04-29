package com.example.employeeservice.dao;

import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 8:32 PM
 */
@Repository
public interface EmployeeDAO extends MongoRepository<Employee, String> {
    @Query("{'firstName':{'$regex':'^?0'}}")
    List<Employee> findByFirstName(String FirstName);

    @Query("{'email':{'$regex':'^?0'}}")
    List<Employee> findByEmail(String Email);

    @Query(value = "{ 'visaStatusList.ID' : ?0 }")
    Employee findByVisaStatusListID(String id);
}

