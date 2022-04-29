package com.example.compositeservice.service;


import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.domain.response.AppWorkFlowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@Service
@FeignClient("application-service")
public interface RemoteApplicationService {

    @Headers
    @PostMapping("application-service/")
    AppWorkFlowResponse add(Optional<ApplicationWorkFlow> applicationWorkFlow);
}
