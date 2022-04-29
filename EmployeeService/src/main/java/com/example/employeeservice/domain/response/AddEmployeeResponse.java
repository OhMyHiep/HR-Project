package com.example.employeeservice.domain.response;


import com.example.employeeservice.domain.Employee;
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