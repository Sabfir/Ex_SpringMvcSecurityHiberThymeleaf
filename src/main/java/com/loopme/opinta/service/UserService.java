package com.loopme.opinta.service;

import com.loopme.opinta.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Integer id);
    User findByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(User user);
}
