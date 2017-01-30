package com.loopme.opinta.dao;

import com.loopme.opinta.model.Client;

import java.util.List;

public interface ClientDAO {
	List<Client> getAll();
	Client getById(Integer id);
	void create(Client client);
	Client update(Client client);
}
