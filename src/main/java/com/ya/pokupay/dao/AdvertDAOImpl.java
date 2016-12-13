package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Advert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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
        if (category != null) {
            query = session.createQuery("from Advert where category = :category");
            query.setParameter("category", category);
        } else {
            query = session.createQuery("from Advert");
        }
        List<Advert> advertsList = query.list();
        return advertsList;
    }

    @Override
    public Advert addAdvert(Advert a) {
        Session session = this.sessionFactory.openSession();
        session.persist(a);
        session.flush();
        return a;
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

    @Override
    public List<Advert> searchAdvert(String searchQuery) throws InterruptedException {
        Session session = this.sessionFactory.openSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Advert.class).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("title")
                .matching(searchQuery)
                .createQuery();

        org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Advert.class);

        List result = hibQuery.list();
        tx.commit();
        session.close();
        return result;
    }
}
