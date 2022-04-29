package com.example.compositeservice.domain.response;

import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompositeResponse {
    private Employee employee;
    private ApplicationWorkFlow application;
}
