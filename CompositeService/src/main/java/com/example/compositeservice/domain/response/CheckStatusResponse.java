package com.example.compositeservice.domain.response;


import com.example.compositeservice.entity.EmployeeService.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckStatusResponse {
    private boolean pass;
    private Employee employee;
   private String message;
}
