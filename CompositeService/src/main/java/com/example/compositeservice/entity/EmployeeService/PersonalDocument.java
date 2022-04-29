package com.example.compositeservice.entity.EmployeeService;

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
public class PersonalDocument {
    private String ID;
    private String path;
    private String title;
    private String comment;
    private Date createDate;
}
