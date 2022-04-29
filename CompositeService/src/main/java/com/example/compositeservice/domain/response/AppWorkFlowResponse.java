package com.example.compositeservice.domain.response;



import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppWorkFlowResponse {
    private ResponseStatus status;
    private ApplicationWorkFlow applicationWorkFlow;
}
