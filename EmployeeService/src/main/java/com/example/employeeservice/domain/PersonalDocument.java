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
public class PersonalDocument {

    @Id
    @ApiModelProperty(notes = "The database generated PersonalDocument ID")
    private String ID;
    private String path;
    private String title;
    private String comment;
    private Date createDate;
}
