package com.example.compositeservice.service;

import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.domain.request.ApplicationFormRequest;
import com.example.compositeservice.domain.response.AppWorkFlowResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CompositeService {

    private RemoteEmployeeService remoteEmployeeService;
    private RemoteApplicationService remoteApplicationService;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setEmployeeService(RemoteEmployeeService remoteEmployeeService) {
        this.remoteEmployeeService = remoteEmployeeService;
    }

    @Autowired
    public void setRemoteApplicationService(RemoteApplicationService remoteApplicationService) {
        this.remoteApplicationService = remoteApplicationService;
    }

    public Employee addEmployeeApplication(@RequestBody ApplicationFormRequest application){
        return remoteEmployeeService.addEmployee(application);
    }

    public AppWorkFlowResponse add(Optional<ApplicationWorkFlow> applicationWorkFlow){
        return remoteApplicationService.add(applicationWorkFlow);
    }

    public Optional<Employee> getEmployeeById(String id){
        return remoteEmployeeService.getEmployeeById(id);
    }

    public ApplicationWorkFlow submit(ApplicationWorkFlow application){
        String requestURL="http://localhost:9000/application-service/application/upload";
        HttpHeaders header= new HttpHeaders();
        header.set("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInBlcm1pc3Npb25zIjpbeyJhdXRob3JpdHkiOiJIUiJ9LHsiYXV0aG9yaXR5IjoiRW1wbG95ZWUifV19.3siPcEVo9TUcA8zzmsEFXKN-w8jTw-ycwj2RM1cbZlc");
        HttpEntity<ApplicationWorkFlow> request=new HttpEntity<ApplicationWorkFlow>(application,header);
        ResponseEntity<AppWorkFlowResponse> responseEntity = restTemplate.exchange(requestURL, HttpMethod.POST,request,AppWorkFlowResponse.class);
        return responseEntity.getBody().getApplicationWorkFlow();
    }

    public Employee updateEmployee(Employee e){
        return remoteEmployeeService.updateEmployee(e);
    }
    public Boolean checkApplicationStatus(String id){
        String requestURL=String.format("http://localhost:9000/application-service/employee/%s",id);
        System.out.println("request Url:"+ requestURL);
        HttpHeaders header= new HttpHeaders();
        header.set("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInBlcm1pc3Npb25zIjpbeyJhdXRob3JpdHkiOiJIUiJ9LHsiYXV0aG9yaXR5IjoiRW1wbG95ZWUifV19.3siPcEVo9TUcA8zzmsEFXKN-w8jTw-ycwj2RM1cbZlc");
        HttpEntity<String> request=new HttpEntity<String>(null,header);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET,request,Boolean.class);
        System.out.println(responseEntity.getBody());
        return  responseEntity.getBody();
    }
}
