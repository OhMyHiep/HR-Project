package com.example.compositeservice.domain.response;

import com.example.compositeservice.entity.EmployeeService.Employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddEmployeeResponse {
    Employee employee;
//    String EmployeeId;
    ServiceStatus serviceStatus;
}


//AddEmployeeResponse.builder()
//        .employee(employeeService.save(newEmployee))
//        .serviceStatus(ServiceStatus.builder().success(true).build())
//        .build();