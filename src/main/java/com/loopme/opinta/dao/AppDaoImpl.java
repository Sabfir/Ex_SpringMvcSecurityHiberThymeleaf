package com.loopme.opinta.dao;

import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(App app) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(app);
    }
}
