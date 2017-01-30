package com.loopme.opinta.service;

import com.loopme.opinta.dao.PhoneDAO;
import com.loopme.opinta.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
	@Autowired
	private PhoneDAO dao;

	@Override
	@Transactional
	public List<Phone> getAll() {
		return dao.getAll();
	}

	@Override
	@Transactional
	public Phone getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	@Transactional
	public void create(Phone phone) {
		dao.create(phone);
	}

	@Override
	@Transactional
	public Phone update(Phone phone) {
		return dao.update(phone);
	}
}
