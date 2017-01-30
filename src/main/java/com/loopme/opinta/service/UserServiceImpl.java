package com.loopme.opinta.service;

import com.loopme.opinta.dao.RoleDao;
import com.loopme.opinta.dao.UserDao;
import com.loopme.opinta.model.Role;
import com.loopme.opinta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getOne(1L));
            user.setRoles(roles);
        }
        userDao.save(user);
    }
}
