package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Advert;
import org.hibernate.*;
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
    public List<Advert> listAdverts(String category, String orderByCriteria, String user) {
        Query query;
        Session session = this.sessionFactory.getCurrentSession();
        if (!category.equals("Все категории")) {
            switch (orderByCriteria) {
                case "От дешевых к дорогим":
                    query = session.createQuery("from Advert a where category = :category order by a.price ASC");
                    break;
                case "От дорогих к дешевым":
                    query = session.createQuery("from Advert a where category = :category order by a.price DESC");
                    break;
                case "Самые новые":
                    query = session.createQuery("from Advert a where category = :category order by a.date DESC");
                    break;
                case "Самые старые":
                    query = session.createQuery("from Advert a where category = :category order by a.date ASC");
                    break;
                default:
                    query = session.createQuery("from Advert a where category = :category order by a.date DESC");
                    break;
            }
            query.setParameter("category", category);

        } else {
            if (!user.equals("not")) {
                query = session.createQuery("from Advert a where authorUsername = :user order by a.date DESC");
                query.setParameter("user", user);
            } else {
                switch (orderByCriteria) {
                    case "От дешевых к дорогим":
                        query = session.createQuery("from Advert a order by a.price ASC");
                        break;
                    case "От дорогих к дешевым":
                        query = session.createQuery("from Advert a order by a.price DESC");
                        break;
                    case "Самые новые":
                        query = session.createQuery("from Advert a order by a.date DESC");
                        break;
                    case "Самые старые":
                        query = session.createQuery("from Advert a order by a.date ASC");
                        break;
                    default:
                        query = session.createQuery("from Advert a order by a.date DESC");
                        break;
                }
            }
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
    public String deleteAdvert(Integer advertId) {
        Session session = this.sessionFactory.openSession();
        session.delete(getAdvertById(advertId));
        session.flush();
        return "Advert deleted!";
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
