package com.loopme.opinta.service;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Integer id);
    User findByUsername(String username);
    void save(User user, String username);
    void saveAnonymously(User user);
    void update(Principal principal, User user);
    void delete(Principal principal, User user);
    void checkPermission(Principal principal, User user);
    boolean hasRole(Principal principal, Role role);
}
