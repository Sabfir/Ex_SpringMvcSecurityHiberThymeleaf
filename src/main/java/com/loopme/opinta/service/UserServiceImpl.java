package com.loopme.opinta.service;

import com.loopme.opinta.dao.AppDao;
import com.loopme.opinta.dao.UserDao;
import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AppDao appDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user, String username) {
        User currentUser = userDao.findByUsername(username);
        if (currentUser.getRole().equals(Role.ROLE_OPERATOR) && !user.getRole().equals(Role.ROLE_PUBLISHER)) {
            throw new IllegalArgumentException("Can't create " + user.getRole() + ". Operator can create only Publishers");
        }
        saveAnonymously(user);
    }

    @Override
    @Transactional
    public void saveAnonymously(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_PUBLISHER);
        }
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(Principal principal, User user) {
        canEdit(principal, user);
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(Principal principal, User user) {
        canEdit(principal, user);
        if (!appDao.getByUser(user).isEmpty()) {
            throw new IllegalArgumentException("Can't delete user! Please delete user's applications first!");
        }
        userDao.delete(user);
    }

    @Override
    @Transactional
    public boolean canEdit(Principal principal, User user) {
        if (findByUsername(principal.getName()).getId().equals(user.getId())) {
            throw new IllegalArgumentException("You can't change your own account");
        }
        if (hasRole(principal, Role.ROLE_OPERATOR) && !user.getRole().equals(Role.ROLE_PUBLISHER)) {
            throw new IllegalArgumentException("Can't work with " + user.getRole() + ". Operator can work with Publishers only");
        }
        return true;
    }

    @Override
    public boolean hasRole(Principal principal, Role role) {
        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        return roles.contains(role.name());
    }
}
