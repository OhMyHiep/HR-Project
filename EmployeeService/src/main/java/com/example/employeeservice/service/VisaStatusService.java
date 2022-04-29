package com.example.employeeservice.service;

import com.example.employeeservice.dao.VisaStatusDAO;
import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.domain.response.ServiceStatus;
import com.example.employeeservice.domain.response.VisaStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Vivian Ma
 * @created 04/04/2022 - 7:30 PM
 */
@Service
public class VisaStatusService {

    private VisaStatusDAO visaStatusDAO;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }


    public VisaStatus saveVisaStatus(VisaStatus visaStatus){
        return visaStatusDAO.save(visaStatus);
    }

    public VisaStatusResponse getVisaStatusById(String id) {
        // 1. get all employee --- listof employee
        // 2. for each of employee, go through the visastatuslist nad compare the ID. USe .equal
        // break
       List <Employee> employeeList = employeeService.getAll();
       Employee employee = null;

       VisaStatus visaStatus = null;
       for(Employee e:employeeList){
           if(e.getVisaStatusList() == null)
               continue;
           for(VisaStatus v: e.getVisaStatusList()){
//               System.out.println("v ID:"+v.getID());
//               System.out.println("found "+v.getID().equals(id));
               if(v.getID().equals(id)){
                   employee = e;
                   visaStatus = v;
//                   System.out.println("found");
                   break;
               }
           }
       }

//        Optional<VisaStatus> visaStatus = visaStatusDAO.findById(id);

        if (visaStatus == null || employee == null) {
            return VisaStatusResponse.builder()
                    .serviceStatus(ServiceStatus.builder()
                            .success(false)
                            .errorMessage("This VisaStatus Not Found").build())
                    .build();
        }
        //Calculate valid days for Visa
        long diffInMillies = Math.abs(visaStatus.getEndDate().getTime() - visaStatus.getStartDate().getTime());
        Integer diff = Math.toIntExact(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS));

        return VisaStatusResponse.builder().name(employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName())
                .visaType(visaStatus.getVisaType())
                .activeFlag(visaStatus.getActiveFlag())
                .expirationDate(visaStatus.getEndDate())
                .validDays(diff)
                .build();
    }

    public List<VisaStatusResponse> getActiveVisaStatus(Integer page){

        Slice<VisaStatus> activateVisaList = visaStatusDAO.findByActiveFlag(true, PageRequest.of(page, 20, Sort.by("ID")));
        System.out.println("all the activate: " + activateVisaList);
        List<VisaStatusResponse> VisaList = new ArrayList<>();
        for(VisaStatus v:activateVisaList){
            VisaList.add(getVisaStatusById(v.getID()));
        }
        return VisaList;

    }
}
