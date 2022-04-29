package com.example.compositeservice.service;

import com.example.compositeservice.domain.request.ApplicationFormRequest;
import com.example.compositeservice.domain.response.AddEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@FeignClient("employee-service")
public interface RemoteEmployeeService {
    @PostMapping("employee-service/")
    Employee addEmployee(@RequestBody ApplicationFormRequest a);

    @PostMapping("employee-service/update")
    public Employee updateEmployee(@RequestBody Employee e);

    @GetMapping("employee-service/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id);

}
