package com.loopme.opinta.dao;

import com.loopme.opinta.model.Phone;

import java.util.List;

public interface PhoneDAO {
	List<Phone> getAll();
	Phone getById(Integer id);
	void create(Phone phone);
	Phone update(Phone phone);
}
