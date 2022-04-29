package com.example.authentication.dao.Impl;

import com.example.authentication.dao.RoleDAO;
import com.example.authentication.entity.Role;
import com.example.authentication.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Override
    public Role getRole(String role) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Role dbRole = null;
        try{
            tx = session.beginTransaction();
            List<Role> rl = session.createQuery("from Role r where r.RoleName = :name").setParameter("name", role).list();
            dbRole = rl.get(0);
            tx.commit();
        }catch (Exception e){
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return dbRole;
    }
}
