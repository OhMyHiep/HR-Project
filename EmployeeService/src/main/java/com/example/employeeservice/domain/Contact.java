package com.example.employeeservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 5:23 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {
    @Id
    @ApiModelProperty(notes = "The database generated contact ID")
    private String ID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cellPhone;
    private String alternatePhone;
    private String email;
    private String relationship;
}
