package com.GroupProject.applicationservice.dao.Impl;

import com.GroupProject.applicationservice.dao.AbstractHibernateDAO;
import com.GroupProject.applicationservice.entity.ApplicationWorkFlow;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ApplicationDaoImpl extends AbstractHibernateDAO<ApplicationWorkFlow> {

    public ApplicationDaoImpl(){
        setClazz(ApplicationWorkFlow.class);
    }

    public ApplicationWorkFlow findById(BigInteger id){
        return getCurrentSession().get(clazz, id);
    }

    public List<ApplicationWorkFlow> getAppByEmployeeID(String employeeId){
   List<ApplicationWorkFlow> app= getCurrentSession().createQuery("" +
                "from ApplicationWorkFlow a " +
                "where a.employeeID=:employeeID").setParameter("employeeID",employeeId).list();
    return app;
    }
}
