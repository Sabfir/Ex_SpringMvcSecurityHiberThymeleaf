package com.loopme.opinta.service;

import com.loopme.opinta.model.Client;

import java.util.List;

public interface ClientService {
	List<Client> getAll();
	Client getById(Integer id);
	void create(Client client);
	Client update(Client client);
}
