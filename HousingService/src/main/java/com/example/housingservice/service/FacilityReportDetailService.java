package com.example.housingservice.service;

import com.example.housingservice.dao.FacilityReportDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityReportDetailService {

    private FacilityReportDetailDAO facilityReportDetailDAO;

    @Autowired
    public void setFacilityReportDetailDAO(FacilityReportDetailDAO facilityReportDetailDAO){
        this.facilityReportDetailDAO = facilityReportDetailDAO;
    }
}
