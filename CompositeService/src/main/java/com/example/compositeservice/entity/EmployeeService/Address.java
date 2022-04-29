package com.example.compositeservice.entity.EmployeeService;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String ID;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}
