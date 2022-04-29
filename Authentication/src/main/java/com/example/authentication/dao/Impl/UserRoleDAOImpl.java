package com.example.authentication.dao.Impl;

import com.example.authentication.dao.UserRoleDAO;
import com.example.authentication.entity.UserRole;
import com.example.authentication.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
    @Override
    public void addUR(UserRole userRole) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(userRole);
            tx.commit();
        }catch (Exception e){
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
