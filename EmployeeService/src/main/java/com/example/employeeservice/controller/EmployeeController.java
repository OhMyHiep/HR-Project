package com.example.employeeservice.controller;

import com.example.employeeservice.domain.*;
import com.example.employeeservice.domain.request.ApplicationFormRequest;
import com.example.employeeservice.domain.request.WorkAuth;
import com.example.employeeservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private VisaStatusService visaStatusService;
    private AddressService addressService;
    private ContactService contactService;
    private PersonalDocumentService personalDocumentService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService) {this.visaStatusService = visaStatusService;}

    @Autowired
    public void setAddressService(AddressService addressService) {this.addressService = addressService;}

    @Autowired
    public void setContactService(ContactService contactService) {this.contactService = contactService;}

    @Autowired
    public void setPersonalDocumentService(PersonalDocumentService personalDocumentService) {this.personalDocumentService = personalDocumentService;}

    @PostMapping
    public Employee addEmployee(@RequestBody ApplicationFormRequest application) {
        List<VisaStatus> visaStatusList =  new ArrayList<>();
        if(!application.getWorkAuth().getIsCitizen()){//not US citizen
            VisaStatus visaStatus = new VisaStatus();
            visaStatus.setActiveFlag(true);
            visaStatus.setVisaType(application.getWorkAuth().getAuthorization());
            visaStatus.setStartDate(application.getWorkAuth().getStartDate());
            visaStatus.setEndDate(application.getWorkAuth().getEndDate());
            visaStatus.setLastModificationDate(application.getApplicationSubmittedTime());
            visaStatusList.add(visaStatusService.saveVisaStatus(visaStatus));
        }else{//US citizen
            visaStatusList.add(
                visaStatusService.saveVisaStatus(
                    VisaStatus.builder().visaType(application.getWorkAuth().getAuthorization()).build()
                )
            );
        }

        List<Address> addressList = new ArrayList<>();
        addressList.add(addressService.save(application.getAddress()));

        Contact newEmergency = contactService.save(application.getEmergency());
        Contact newReference = null;
        if(application.getReference()!=null) newReference = contactService.save(application.getReference());

        List<PersonalDocument> personalDocumentList = application.getPersonalDocumentList()
                .stream().map(
                        (doc)->{
                            return personalDocumentService.save(doc);
                        }
                ).collect(Collectors.toList());

        Employee newEmployee = new Employee();
        newEmployee.setAddress(addressList);
        newEmployee.setPersonalDocumentList(personalDocumentList);
        newEmployee.setEmergency(newEmergency);
        newEmployee.setReference(newReference);
        newEmployee.setVisaStatusList(visaStatusList);
//        --------------------------------------------------
        newEmployee.setFirstName(application.getFirstName());
        newEmployee.setLastName(application.getLastName());
        newEmployee.setMiddleName(application.getMiddleName());
        newEmployee.setPreferredName(application.getPreferredName());
        newEmployee.setEmail(application.getEmail());
        newEmployee.setCellPhone(application.getCellPhone());
        newEmployee.setAlternatePhone(application.getAlternatePhone());
        newEmployee.setGender(application.getGender());
        newEmployee.setSSN(application.getSSN());
        newEmployee.setDOB(application.getDOB());
        newEmployee.setStartDate(application.getStartDate());
        newEmployee.setEndDate(application.getEndDate());
        newEmployee.setDriverLicense(application.getDriverLicense());
        newEmployee.setDriverLicenseExpiration(application.getDriverLicenseExpiration());
        return employeeService.saveEmployee(newEmployee);
    }

}
