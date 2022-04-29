package com.GroupProject.applicationservice.domain.response;


import com.GroupProject.applicationservice.entity.ApplicationWorkFlow;
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
