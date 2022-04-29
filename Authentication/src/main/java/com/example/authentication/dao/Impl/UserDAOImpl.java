package com.example.authentication.dao.Impl;

import com.example.authentication.dao.UserDAO;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public Optional<User> loadUserByUsername(String Username) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<User> userList = null;
        try{
            tx = session.beginTransaction();
            String sql = "Select u From User u where u.username = :username";
            userList = session.createQuery(sql).setParameter("username",Username).list();
            tx.commit();
        }catch (Exception e){
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userList.stream().filter(user -> Username.equals(user.getUsername())).findAny();
    }

    @Override
    public List<Role> getRoleList(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Role> roleList = null;
        try{
            tx = session.beginTransaction();
            String sql = "SELECT r " +
                         "FROM UserRole ur " +
                         "JOIN Role r on r.ID = ur.role.ID and ur.user.ID = :id";
            roleList = session.createQuery(sql,Role.class).setParameter("id",user.getID()).list();
            tx.commit();
        }catch (Exception e){
            if(tx == null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return roleList;
    }

    @Override
    public Boolean checkExist(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from User u where u.username = :username or u.email = :email";
        List<User> userList = session.createQuery(sql).setParameter("username",user.getUsername()).setParameter("email",user.getEmail()).list();
        session.close();
        return userList.size() != 0;
    }

    @Override
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(user);
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
