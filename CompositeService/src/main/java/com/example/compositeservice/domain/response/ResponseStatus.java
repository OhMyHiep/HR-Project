package com.example.compositeservice.domain.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatus {
    private Boolean success;
    private String message;
}
