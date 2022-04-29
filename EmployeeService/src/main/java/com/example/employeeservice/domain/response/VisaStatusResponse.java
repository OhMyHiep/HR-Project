package com.example.employeeservice.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisaStatusResponse {
    private String name;
    private String visaType;
    private Date expirationDate;
    private Integer validDays;
    private Boolean activeFlag;
    ServiceStatus serviceStatus;
}
