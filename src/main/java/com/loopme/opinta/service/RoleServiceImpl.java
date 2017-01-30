package com.loopme.opinta.service;

import com.loopme.opinta.dao.RoleDao;
import com.loopme.opinta.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }
}
