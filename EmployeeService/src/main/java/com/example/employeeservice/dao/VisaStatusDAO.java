package com.example.employeeservice.dao;

import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.VisaStatus;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;


@Repository
public interface VisaStatusDAO extends PagingAndSortingRepository<VisaStatus, String> {
    Slice  <VisaStatus> findByActiveFlag(Boolean activeFlag, Pageable pageable);
}
