package com.loopme.opinta.dao;

import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;

import java.util.List;

public interface AppDao {
    void save(App app);
    List<App> getAll();
    List<App> getByUser(User user);
    App getById(Integer id);
    void update(App app);
    void delete(App app);
}
