package com.GroupProject.applicationservice.service;


import com.GroupProject.applicationservice.dao.Impl.ApplicationDaoImpl;
import com.GroupProject.applicationservice.domain.response.AppWorkFlowResponse;
import com.GroupProject.applicationservice.domain.response.ResponseStatus;
import com.GroupProject.applicationservice.entity.ApplicationWorkFlow;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private ApplicationDaoImpl applicationDao;

    @Autowired
    public void setApplicationDao(ApplicationDaoImpl applicationDao) {
        this.applicationDao = applicationDao;
    }

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Transactional
    public AppWorkFlowResponse add(Optional<ApplicationWorkFlow> applicationWorkFlow){
        BigInteger id=null;
        if(applicationWorkFlow.isPresent()){
            id= applicationDao.add(applicationWorkFlow.get());
        }
        return id==null? AppWorkFlowResponse.builder()
                .status(ResponseStatus.builder()
                        .success(false)
                        .message("Failed to Add or Application not present")
                        .build())
                .build():
                AppWorkFlowResponse.builder()
                        .status(ResponseStatus.builder()
                                .success(true)
                                .message("Added sucessfully")
                                .build())
                        .applicationWorkFlow(applicationWorkFlow.get())
                        .build();
    }

    @Transactional
    public AppWorkFlowResponse findByID(Optional<BigInteger> id,Optional<String> email){
        ApplicationWorkFlow app= applicationDao.findById(id.get());
        AppWorkFlowResponse response=AppWorkFlowResponse.builder()
                .applicationWorkFlow(app)
                .build();

        if(app!=null && app.getStatus().equals("pending")){
            response.setStatus(ResponseStatus.builder()
                    .message("please wait for HR to review your application")
                    .build());
        }
        else if(app!=null && app.getStatus().equals("rejected")){
            response.setStatus(ResponseStatus.builder()
                    .message("rejected")
                    .build());
            String comment= app.getComment();
           rabbitTemplate.convertAndSend("Application","rejection",email.get()+"+++"+comment);
        }
        return response;
    }

    @Transactional
    public Boolean getApplicationStatus(Optional<String> employeeID){
        List<ApplicationWorkFlow> appList= applicationDao.getAppByEmployeeID(employeeID.get());

        if (appList.size()>0){
            ApplicationWorkFlow app=appList.get(0);
            if(app!=null && app.getStatus().equals("accepted")) return true;
        }
        return false;
    }
}
