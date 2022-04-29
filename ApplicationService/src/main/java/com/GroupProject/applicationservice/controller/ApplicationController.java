package com.GroupProject.applicationservice.controller;


import com.GroupProject.applicationservice.domain.response.AppWorkFlowResponse;
import com.GroupProject.applicationservice.entity.ApplicationWorkFlow;
import com.GroupProject.applicationservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
public class ApplicationController {

    private ApplicationService applicationService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("{id}")
    public AppWorkFlowResponse findById(@PathVariable Optional<BigInteger> id,@RequestParam(required = false) Optional<String> email){
        return id.isPresent() && email.isPresent()? applicationService.findByID(id,email):null;
    }

    @GetMapping("employee/{employeeID}")
    public Boolean getApplicationStatus(@PathVariable(required = false) Optional<String> employeeID){
        if(employeeID.isPresent()){
            return applicationService.getApplicationStatus(employeeID);
        }
        return false;
    }

    @PostMapping("application/upload")
    public AppWorkFlowResponse addApplication(@RequestBody Optional<ApplicationWorkFlow> application){
        return applicationService.add(application);
    }
}
