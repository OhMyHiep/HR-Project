package com.example.housingservice.service;

import com.example.housingservice.dao.FacilityReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityReportService {
    private FacilityReportDAO facilityReportDAO;

    @Autowired
    public void setFacilityReportDAO(FacilityReportDAO facilityReportDAO){
        this.facilityReportDAO = facilityReportDAO;
    }
}
