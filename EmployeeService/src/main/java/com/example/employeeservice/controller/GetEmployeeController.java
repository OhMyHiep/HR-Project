package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.request.EmployeeFilterRequest;
import com.example.employeeservice.domain.response.GetEmployeeResponse;
import com.example.employeeservice.domain.response.ServiceStatus;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class GetEmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/ByFilter")
    public GetEmployeeResponse getEmployeeByFilter(@RequestBody EmployeeFilterRequest employeeFilterRequest) {
        System.out.println("employeeFilterRequest" + employeeFilterRequest);
        List<Employee> employees = employeeService.employeeFilter(employeeFilterRequest);
        if (employees == null || employees.size() == 0) {
            return GetEmployeeResponse.builder()
                    .serviceStatus(ServiceStatus.builder().success(false).errorMessage("not found or invalid filter").build())
                    .employees(null).build();
        } else {
            return GetEmployeeResponse.builder()
                    .serviceStatus(ServiceStatus.builder().success(true).errorMessage("").build())
                    .employees(employees).build();
        }

    }

    @GetMapping("{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }


    @PostMapping("/update")
    public Employee updateEmployee(@RequestBody Employee e){
        return employeeService.updateEmployee(e);

}}
