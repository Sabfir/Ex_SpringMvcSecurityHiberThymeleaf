package com.loopme.opinta.service;

import com.loopme.opinta.model.App;

import java.util.List;

public interface AppService {
    List<App> getAll();
    App getById(Integer id);
    List<App> getByUser(String username);
    void save(App app);
    void update(App app);
    void delete(App app);
    boolean isCreatedByUser(Integer id, String username);
}
