package com.example.employeeservice.service;

import com.example.employeeservice.dao.*;
import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.Contact;
import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.domain.request.EmployeeFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Vivian Ma
 * @created 04/04/2022 - 10:27 AM
 */

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    public List<Employee> employeeFilter(EmployeeFilterRequest employeeFilterRequest) {
        System.out.println(employeeFilterRequest);

        if (employeeFilterRequest.getValue() == null && employeeFilterRequest.getFilter() == null) {
            return (List<Employee>) employeeDAO.findAll();
        }

        if (employeeFilterRequest.getValue() == null || employeeFilterRequest.getFilter() == null || employeeFilterRequest.getFilter() == "" || employeeFilterRequest.getValue() == "")
            System.out.println("InvalidPropertyException");


        switch (employeeFilterRequest.getFilter().toLowerCase().replace(" ", "")) {
            case "firstname":

                return employeeDAO.findByFirstName(employeeFilterRequest.getValue());
            case "email":
                return employeeDAO.findByEmail(employeeFilterRequest.getValue());
            default:
                System.out.println("Invalid filter");
                break;
        }
        return null;

    }


    public Optional<Employee> getEmployeeById(String id) {
        return employeeDAO.findById(id);

    }

    public List<Employee> getAll() {
        return employeeDAO.findAll(Sort.by("ID"));

    }


    public Employee getEmployeeByVisaStatusId(String id) {
        return employeeDAO.findByVisaStatusListID(id);

    }


    public Employee updateEmployee(Employee e) {

        Optional<Employee> updateEmployee = getEmployeeById(e.getID());
        Employee employee = updateEmployee.get();
        System.out.println("Employee" + e);
        System.out.println("before:" + employee);
        if (e.getEmail() != null) {
            employee.setEmail(e.getEmail());
        }
        if (e.getFirstName() != null) {
            employee.setFirstName(e.getFirstName());
        }
        if (e.getLastName() != null) {
            employee.setLastName(e.getLastName());
        }
        if (e.getMiddleName() != null) {
            employee.setMiddleName(e.getMiddleName());
        }
        if (e.getPreferredName() != null) {
            employee.setPreferredName(e.getPreferredName());
        }
        if (e.getCellPhone() != null) {
            employee.setCellPhone(e.getCellPhone());
        }
        if (e.getAlternatePhone() != null) {
            employee.setAlternatePhone(e.getAlternatePhone());
        }
        if (e.getGender() != null) {
            employee.setGender(e.getGender());
        }
        if (e.getSSN() != null) {
            employee.setSSN(e.getSSN());
        }
        if (e.getDOB() != null) {
            employee.setDOB(e.getDOB());
        }
        if (e.getDriverLicense() != null) {
            employee.setDriverLicense(e.getDriverLicense());
        }
        if (e.getDriverLicenseExpiration() != null) {
            employee.setDriverLicenseExpiration(e.getDriverLicenseExpiration());
        }

        //lazy implementation
        if (e.getEmergency() != null) {
            employee.setEmergency(e.getEmergency());
            Contact emer1 = e.getEmergency();
            Contact emer;

            //update emergency in Employee collection
            emer= employee.getEmergency();
            if (emer1.getFirstName() != null) emer.setFirstName(emer1.getFirstName());
            if (emer1.getLastName() != null) emer.setLastName(emer1.getLastName());
            if (emer1.getEmail() != null) emer.setEmail(emer1.getEmail());
            if (emer1.getMiddleName() != null) emer.setMiddleName(emer1.getMiddleName());
            if (emer1.getRelationship() != null) emer.setRelationship(emer1.getRelationship());
            if (emer1.getAlternatePhone() != null) emer.setAlternatePhone(emer1.getAlternatePhone());
            if (emer1.getCellPhone() != null) emer.setCellPhone(emer1.getCellPhone());

            //update emergency in Contact collection
            emer = contactService.getById(e.getEmergency().getID()).get();
            if (emer1.getFirstName() != null) emer.setFirstName(emer1.getFirstName());
            if (emer1.getLastName() != null) emer.setLastName(emer1.getLastName());
            if (emer1.getEmail() != null) emer.setEmail(emer1.getEmail());
            if (emer1.getMiddleName() != null) emer.setMiddleName(emer1.getMiddleName());
            if (emer1.getRelationship() != null) emer.setRelationship(emer1.getRelationship());
            if (emer1.getAlternatePhone() != null) emer.setAlternatePhone(emer1.getAlternatePhone());
            if (emer1.getCellPhone() != null) emer.setCellPhone(emer1.getCellPhone());
            contactService.save(emer);
        }

        if (e.getReference() != null) {
            employee.setReference(e.getReference());
        }
        if (e.getVisaStatusList() != null) {
            employee.setVisaStatusList(e.getVisaStatusList());
        }
        if (e.getAddress() != null) {
            employee.setAddress(e.getAddress());
        }


//
        return employeeDAO.save(employee);
//        return  null;


    }


}
