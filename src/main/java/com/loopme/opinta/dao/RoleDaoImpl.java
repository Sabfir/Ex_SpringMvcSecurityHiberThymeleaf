//package com.loopme.opinta.dao;
//
//import com.loopme.opinta.model.Role;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class RoleDaoImpl implements RoleDao {
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Override
//    public Role getOne(long id) {
//        Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.get(Role.class, id);
//        return role;
//    }
//
//    @Override
//    public void save(Role role) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(role);
//    }
//}
