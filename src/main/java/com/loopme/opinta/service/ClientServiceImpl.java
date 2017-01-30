//package com.loopme.opinta.service;
//
//import com.loopme.opinta.dao.ClientDAO;
//import com.loopme.opinta.model.Client;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class ClientServiceImpl implements ClientService {
//	@Autowired
//	private ClientDAO dao;
//
//	@Override
//	@Transactional
//	public List<Client> getAll() {
//		return dao.getAll();
//	}
//
//	@Override
//	@Transactional
//	public Client getById(Integer id) {
//		return dao.getById(id);
//	}
//
//	@Override
//	@Transactional
//	public void create(Client client) {
//		dao.create(client);
//	}
//
//	@Override
//	@Transactional
//	public Client update(Client client) {
//		return dao.update(client);
//	}
//}
