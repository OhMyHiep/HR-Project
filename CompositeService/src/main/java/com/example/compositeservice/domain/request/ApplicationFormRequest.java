package com.example.compositeservice.domain.request;

import com.example.compositeservice.entity.EmployeeService.Address;
import com.example.compositeservice.entity.EmployeeService.Contact;
import com.example.compositeservice.entity.EmployeeService.PersonalDocument;
import com.example.compositeservice.entity.EmployeeService.VisaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Vivian Ma
 * @created 02/04/2022 - 6:21 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationFormRequest {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("preferredName")
    private String preferredName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cellPhone")
    private String cellPhone;

    @JsonProperty("alternatePhone")
    private String alternatePhone;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("ssn")
    private String SSN;

    @JsonProperty("dob")
    private Date DOB;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonProperty("driverLicense")
    private String driverLicense;

    @JsonProperty("driverLicenseExpiration")
    private Date driverLicenseExpiration;

//------------------ Contact -----------------------------------
    @JsonProperty("emergency")
    private Contact emergency;

    @JsonProperty("reference")
    private Contact reference;

//------------------ ArrayOfAddress ------------------------------
    @JsonProperty("address")
    private Address address;

//------------------ WorkAuthorization Question -------------------------
    @JsonProperty("workAuth")
    private WorkAuth workAuth;

//-----------------  ArrayOfPersonalDocument ---------------------------
    @JsonProperty("personalDocumentList")
    private List<PersonalDocument> personalDocumentList;

    private Date applicationSubmittedTime;
}
