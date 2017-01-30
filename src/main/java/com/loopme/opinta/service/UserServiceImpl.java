package com.loopme.opinta.service;

//import com.loopme.opinta.dao.RoleDao;
import com.loopme.opinta.dao.UserDao;
import com.loopme.opinta.enums.Role;
//import com.loopme.opinta.model.Role;
import com.loopme.opinta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
//    @Autowired
//    private RoleDao roleDao;

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
        if (user.getRole() == null) {
            user.setRole(Role.PUBLISHER);
        }
        userDao.save(user);
    }
}
