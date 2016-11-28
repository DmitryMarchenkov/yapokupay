package com.ya.pokupay.dao;

import com.ya.pokupay.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("FIND USER");

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        List<User> users = query.list();

        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(User user) {
        System.out.println("SAVE USER");
        Session session = this.sessionFactory.openSession();
//        session.persist(user);
        session.save(user);
        session.flush();
    }
}
