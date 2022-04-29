package com.example.housingservice.service;

import com.example.housingservice.dao.FacilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityService {
    private FacilityDAO facilityDAO;

    @Autowired
    public void setFacilityDAO(FacilityDAO facilityDAO){
        this.facilityDAO = facilityDAO;
    }
}
