package com.loopme.opinta.service;

import com.loopme.opinta.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
