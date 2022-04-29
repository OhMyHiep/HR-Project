package com.example.compositeservice.entity.EmployeeService;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Vivian Ma
 * @created 03/04/2022 - 2:23 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private String ID;
    private Integer userID;
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
    private Integer houseID;

    private  List<Address> address;

    private Contact emergency;
    private Contact reference;

    private List<VisaStatus> visaStatusList;

    private List<PersonalDocument> personalDocumentList;
}
