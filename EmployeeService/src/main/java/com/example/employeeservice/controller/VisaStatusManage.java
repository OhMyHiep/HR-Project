package com.example.employeeservice.controller;

import com.example.employeeservice.domain.response.VisaStatusResponse;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("VisaDetails")
public class VisaStatusManage {
    EmployeeService employeeService;
    VisaStatusService visaStatusServicel;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setVisaStatusServicel(VisaStatusService visaStatusServicel) {
        this.visaStatusServicel = visaStatusServicel;
    }

    @GetMapping("/ById")
    public VisaStatusResponse getVisaStatusByVisaId(@RequestParam String id) {
        return visaStatusServicel.getVisaStatusById(id);
    }


    @GetMapping("/ActiveVisa")
    public List<VisaStatusResponse> getAllActivateVisa(@RequestParam Integer page) {
        return visaStatusServicel.getActiveVisaStatus(page);
    }
}
