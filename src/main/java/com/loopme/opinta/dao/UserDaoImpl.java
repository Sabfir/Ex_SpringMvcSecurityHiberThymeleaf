package com.loopme.opinta.dao;

import com.loopme.opinta.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }
}
