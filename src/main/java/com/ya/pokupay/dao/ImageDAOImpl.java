package com.ya.pokupay.dao;

import com.ya.pokupay.model.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ImageDAOImpl implements ImageDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public String save(List<Image> images) {
        if (images.size() == 0) return null;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        for ( int i = 0; i < images.size(); i++ ) {
            session.save(images.get(i));
            if ( i % 20 == 0 ) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
        return "Files successfully uploaded";
    }

    @Override
    public List<Image> getImagesList(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Image where advertid = :advertid");
        query.setParameter("advertid", id);
        List<Image> imageList = query.list();
        return imageList;
    }

    @Override
    public Image getOneImageByAdvertId(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Image where advertid = :advertid");
        query.setParameter("advertid", id);
        query.setMaxResults(1);
        List<Image> imageList = query.list();
        if (imageList.isEmpty()) return null;
        return imageList.get(0);
    }

    @Override
    public Image getImageById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Image where id = :id");
        query.setParameter("id", id);

        List<Image> imageList = query.list();
        if (imageList.isEmpty()) return null;
        return imageList.get(0);
    }
}
