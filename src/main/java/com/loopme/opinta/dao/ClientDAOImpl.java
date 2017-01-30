package com.loopme.opinta.dao;

import com.loopme.opinta.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ClientDAOImpl implements ClientDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Client> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Client.class).list();
	}

	@Override
	public Client getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Client) session.get(Client.class, id);
	}

	@Override
	public void create(Client client) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(client);
	}

	@Override
	public Client update(Client client) {
		Session session = sessionFactory.getCurrentSession();
		return (Client) session.merge(client);
	}
}
