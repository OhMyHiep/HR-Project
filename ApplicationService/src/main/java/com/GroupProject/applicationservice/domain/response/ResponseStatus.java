package com.GroupProject.applicationservice.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatus {
    private Boolean success;
    private String message;
}