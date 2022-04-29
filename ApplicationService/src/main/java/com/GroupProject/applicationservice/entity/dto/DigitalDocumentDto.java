package com.GroupProject.applicationservice.entity.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DigitalDocumentDto {

    private Integer Id;


    private String type;


    private Boolean isRequired;


    private String path;


    private String description;


    private String title;
}
