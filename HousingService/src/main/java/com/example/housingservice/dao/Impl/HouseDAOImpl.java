package com.example.housingservice.dao.Impl;

import com.example.housingservice.dao.HouseDAO;
import com.example.housingservice.entity.House;
import com.example.housingservice.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseDAOImpl implements HouseDAO {

    public List<House> getAllHouses(){
//        return getCurrentSession().createQuery("from House").list();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        return session.createQuery("from House").list();

    }

}
