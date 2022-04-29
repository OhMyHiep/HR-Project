package com.example.authentication.dao.Impl;

import com.example.authentication.dao.RegistrationTokenDAO;
import com.example.authentication.entity.RegistrationToken;
import com.example.authentication.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationTokenDAOImpl implements RegistrationTokenDAO {

    @Override
    public void addToken(RegistrationToken token) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(token);
            tx.commit();
        }catch(Exception e){
            if(tx == null){
                tx.rollback();
            }
            tx.rollback();
        }finally {
            session.close();
        }
    }
}
