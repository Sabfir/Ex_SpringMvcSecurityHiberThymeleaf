//package com.loopme.opinta.dao;
//
//import com.loopme.opinta.model.Phone;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@Slf4j
//public class PhoneDAOImpl implements PhoneDAO {
//	@Autowired
//	SessionFactory sessionFactory;
//
//	@Override
//	public List<Phone> getAll() {
//		Session session = sessionFactory.getCurrentSession();
//		return session.createCriteria(Phone.class).list();
//	}
//
//	@Override
//	public Phone getById(Integer id) {
//		Session session = sessionFactory.getCurrentSession();
//		return (Phone) session.get(Phone.class, id);
//	}
//
//	@Override
//	public void create(Phone phone) {
//		Session session = sessionFactory.getCurrentSession();
//		session.persist(phone);
//	}
//
//	@Override
//	public Phone update(Phone phone) {
//		Session session = sessionFactory.getCurrentSession();
//		return (Phone) session.merge(phone);
//	}
//}
