package com.ya.pokupay.dao;

import com.ya.pokupay.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
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
        Session session = this.sessionFactory.openSession();
        session.save(user);
        session.flush();
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        session.update(user);
        session.flush();
    }
}
