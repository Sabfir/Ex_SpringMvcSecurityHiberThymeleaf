package com.loopme.opinta.service;

import com.loopme.opinta.model.App;

import java.util.List;

public interface AppService {
    void save(App app);
    List<App> getAll();
    List<App> getByUser(String username);
    App getById(Integer id);
    void update(App app);
    void delete(App app);
}
