package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Advert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertDAOImpl implements AdvertDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Advert> listAdverts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Advert> advertsList = session.createQuery("from Advert").list();
//        session.close();
        return advertsList;
    }
}
