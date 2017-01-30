package com.loopme.opinta.dao;

import com.loopme.opinta.model.User;

public interface UserDao {
    User findByUsername(String username);

    void save(User user);
}
