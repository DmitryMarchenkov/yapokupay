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
        Query query;
        Session session = this.sessionFactory.getCurrentSession();
        if (category.equals("Все категории")) {
            query = session.createQuery("from Advert");
        } else {
            query = session.createQuery("from Advert where category = :category");
            query.setParameter("category", category);
        }
        List<Advert> advertsList = query.list();
        return advertsList;
    }

    @Override
    public void addAdvert(Advert a) {
        Session session = this.sessionFactory.openSession();
        session.persist(a);
        session.flush();
    }

    @Override
    public void updateAdvert(Advert a) {
        Session session = this.sessionFactory.openSession();
        session.update(a);
        session.flush();
    }

    @Override
    public Advert getAdvertById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Advert where id = :advertid");
        query.setParameter("advertid", id);

        List<Advert> advertsList = query.list();
        if (advertsList.size() > 0) {
            return advertsList.get(0);
        }
        return null;
    }
}
