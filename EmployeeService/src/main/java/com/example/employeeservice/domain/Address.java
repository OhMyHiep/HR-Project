package com.example.employeeservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    @Id
    @ApiModelProperty(notes = "The database generated Address ID")
    private String ID;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}
