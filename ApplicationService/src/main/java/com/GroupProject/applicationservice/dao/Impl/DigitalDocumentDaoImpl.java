package com.GroupProject.applicationservice.dao.Impl;


import com.GroupProject.applicationservice.dao.AbstractHibernateDAO;
import com.GroupProject.applicationservice.entity.DigitalDocument;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DigitalDocumentDaoImpl extends AbstractHibernateDAO<DigitalDocument> {

    public DigitalDocumentDaoImpl(){
        setClazz(DigitalDocument.class);
    }

    public List<DigitalDocument> getAllDocuments(){
        return getCurrentSession().createQuery("from DigitalDocument").list();
    }


}
