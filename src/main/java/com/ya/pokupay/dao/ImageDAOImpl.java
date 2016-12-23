package com.ya.pokupay.dao;

import com.ya.pokupay.model.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ImageDAOImpl implements ImageDAO {

    private SessionFactory sessionFactory;

    @Resource(name="generalProperties")
    private Properties generalProperties;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public String save(List<Image> images) {
        if (images.size() == 0) return null;

        String imagesUrl = generalProperties.getProperty("imagesUrl");

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        for ( int i = 0; i < images.size(); i++ ) {
            Image image = images.get(i);
            String location = imagesUrl + image.getUser() + "/" + image.getTitle();
            File pathFile = new File(location);
            if (!pathFile.exists()) {
                pathFile.mkdir();
            }

            pathFile = new File(location + "/" + image.getName());

            try {
                image.getFile().transferTo(pathFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            session.save(image);
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
    public String getOneImageByAdvertId(Integer id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Image where advertid = :advertid");
        query.setParameter("advertid", id);
        query.setMaxResults(1);
        List<Image> imageList = query.list();
        if (imageList.isEmpty()) return null;
        return imageList.get(0).getName();
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
