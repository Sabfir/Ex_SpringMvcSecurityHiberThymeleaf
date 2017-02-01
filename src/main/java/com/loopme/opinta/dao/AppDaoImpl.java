package com.loopme.opinta.dao;

import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<App> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(App.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public App getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (App) session.get(App.class, id);
    }

    @Override
    public List<App> getByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(App.class)
                .add(Restrictions.eq("user", user))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public void save(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(app);
    }

    @Override
    public void update(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.update(app);
    }

    @Override
    public void delete(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(app);
    }
}
