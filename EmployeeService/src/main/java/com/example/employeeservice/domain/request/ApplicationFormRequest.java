package com.example.employeeservice.domain.request;

import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.Contact;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationFormRequest {

    //Employee Info:
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferredName;
    private String email;
    private String cellPhone;
    private String alternatePhone;
    private String gender;
    private String SSN;
    private Date DOB;
    private Date startDate;
    private Date endDate;
    private String driverLicense;
    private Date driverLicenseExpiration;
//------------------ Contact -----------------------------------
    private Contact emergency;
    private Contact reference;
//------------------ ArrayOfAddress ------------------------------
    private Address address;
    //------------------ WorkAuthorization Question -------------------------
    private WorkAuth workAuth;
//-----------------  ArrayOfPersonalDocument ---------------------------
    private List<PersonalDocument> personalDocumentList;

    private Date applicationSubmittedTime;
}
