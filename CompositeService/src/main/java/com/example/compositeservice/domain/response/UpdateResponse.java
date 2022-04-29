package com.example.compositeservice.domain.response;


import com.example.compositeservice.entity.EmployeeService.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateResponse {
private Employee employee;
    private String message;
}
