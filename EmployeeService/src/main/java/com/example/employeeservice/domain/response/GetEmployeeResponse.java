package com.example.employeeservice.domain.response;

import com.example.employeeservice.domain.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEmployeeResponse {
   List<Employee> employees;
    ServiceStatus serviceStatus;
}
