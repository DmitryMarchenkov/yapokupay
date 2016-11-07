package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Advert;
import org.hibernate.Query;
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
    public List<Advert> listAdverts(String category) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Advert where category = :category");
        query.setParameter("category", category);
        List<Advert> advertsList = query.list();
//        session.close();
        return advertsList;
    }
}
