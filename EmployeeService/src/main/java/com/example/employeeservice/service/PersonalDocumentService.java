package com.example.employeeservice.service;

import com.example.employeeservice.dao.PersonalDocumentDAO;
import com.example.employeeservice.domain.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vivian Ma
 * @created 04/04/2022 - 7:33 PM
 */
@Service
public class PersonalDocumentService {
    private PersonalDocumentDAO personalDocumentDAO;


    @Autowired
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    public PersonalDocument save(PersonalDocument doc){
        return personalDocumentDAO.save(doc);
    }
}
