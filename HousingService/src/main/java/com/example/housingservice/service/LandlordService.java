package com.example.housingservice.service;

import com.example.housingservice.dao.LandlordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandlordService {
    private LandlordDAO landlordDAO;

    @Autowired
    public void setLandlordDAO(LandlordDAO landlordDAO){
        this.landlordDAO = landlordDAO;
    }
}
