package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> listProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productsList = session.createQuery("from Product").list();
        return productsList;
    }
}
