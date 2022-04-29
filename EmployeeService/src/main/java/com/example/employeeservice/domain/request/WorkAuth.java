package com.example.employeeservice.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkAuth {
    private Boolean isCitizen;
    private String authorization;
    private Date startDate;
    private Date endDate;
}

//isCitizen
// if yes ---- no need to fill out the other 3
// if no ---- fill out the WorkAuth, and its start + end date
