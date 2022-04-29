package com.example.employeeservice.service;

import com.example.employeeservice.dao.AddressDAO;
import com.example.employeeservice.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vivian Ma
 * @created 04/04/2022 - 7:28 PM
 */
@Service
public class AddressService {
    private AddressDAO addressDAO;

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public List<Address> saveAddresses(List<Address> addresses){
        return addressDAO.saveAll(addresses);
    }

    public Address save(Address address){return addressDAO.save(address);}
}
