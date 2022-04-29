package com.example.employeeservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 5:24 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisaStatus {
    @Id
    @ApiModelProperty(notes = "The database generated  VisaStatus  ID")
    private String ID;
    private String visaType;
    private Boolean activeFlag;
    private Date startDate;
    private Date endDate;
    private Date lastModificationDate;
}
