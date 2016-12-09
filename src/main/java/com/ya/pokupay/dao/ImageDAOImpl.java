package com.ya.pokupay.dao;

import com.ya.pokupay.model.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class ImageDAOImpl implements ImageDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void save(List<Image> image) {
        Session session = this.sessionFactory.openSession();
        session.persist(image.get(0));
        session.flush();
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
    public Image getOneImage(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Image where advertid = :advertid");
        query.setParameter("advertid", id);
        query.setMaxResults(1);
        List<Image> imageList = query.list();
        if (imageList.isEmpty()) return null;
        return imageList.get(0);
    }
}
