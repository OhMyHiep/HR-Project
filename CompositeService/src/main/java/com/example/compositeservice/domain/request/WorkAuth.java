package com.example.compositeservice.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("isCitizen")
    private Boolean isCitizen;

    @JsonProperty("authorization")
    private String authorization;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;
}

//isCitizen
// if yes ---- no need to fill out the other 3
// if no ---- fill out the WorkAuth, and its start + end date
