package com.loopme.opinta.service;

import com.loopme.opinta.model.App;

import java.security.Principal;
import java.util.List;

public interface AppService {
    List<App> getAll();
    App getById(Integer id);
    List<App> getByUser(String username);
    void save(App app);
    void update(Principal principal, App app);
    void delete(Principal principal, App app);
    boolean isCreatedByUser(Integer id, String username);
    boolean isCreatedByUser(App app, String username);
    boolean canEdit(Principal principal, App app);
}
