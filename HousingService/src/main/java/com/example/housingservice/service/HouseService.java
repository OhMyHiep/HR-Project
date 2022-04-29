package com.example.housingservice.service;

import com.example.housingservice.dao.HouseDAO;
import com.example.housingservice.dao.Impl.HouseDAOImpl;
import com.example.housingservice.domain.HouseResponse;
import com.example.housingservice.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HouseService {
    private HouseDAO houseDAO;

    @Autowired
    public void setHouseDAO(HouseDAO houseDAO){
        this.houseDAO = houseDAO;
    }

    private HouseDAOImpl houseDAOImpl;

    @Autowired
    public void setHouseDAOImpl(HouseDAOImpl houseDAOImpl) {
        this.houseDAOImpl = houseDAOImpl;
    }



    @Transactional
    public List<HouseResponse> getAllHouses(){
        List<House> houses= houseDAOImpl.getAllHouses();
       return houses.stream()
                .map(h->HouseResponse.builder()
                        .email(h.getLandlord().getEmail())
                        .address(h.getAddress())
                        .landlordName(h.getLandlord().getFirstName()+" "+h.getLandlord().getLastName())
                        .maxOccupant(h.getMaxOccupant())
                        .phone(h.getLandlord().getCellPhone())
                        .build()
                ).collect(Collectors.toList());

    }


}
