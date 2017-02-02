package com.loopme.opinta.service;

import com.loopme.opinta.dao.AppDao;
import com.loopme.opinta.enums.Role;
import com.loopme.opinta.exception.InsufficientPermissionException;
import com.loopme.opinta.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppDao appDao;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<App> getAll() {
        return appDao.getAll();
    }

    @Override
    @Transactional
    public App getById(Integer id) {
        return appDao.getById(id);
    }

    @Override
    @Transactional
    public List<App> getByUser(String username) {
        return appDao.getByUser(userService.findByUsername(username));
    }

    @Override
    @Transactional
    public void save(App app) {
        appDao.save(app);
    }

    @Override
    @Transactional
    public void update(Principal principal, App app) {
        checkPermission(principal, app);
        appDao.update(app);
    }

    @Override
    @Transactional
    public void delete(Principal principal, App app) {
        checkPermission(principal, app);
        appDao.delete(app);
    }

    @Override
    public boolean isCreatedByUser(Integer id, String username) {
        return isCreatedByUser(getById(id), username);
    }

    @Override
    public boolean isCreatedByUser(App app, String username) {
        return app.getUser().getId().equals(userService.findByUsername(username).getId());
    }

    @Override
    public void checkPermission(Principal principal, App app) {
        if (userService.hasRole(principal, Role.ROLE_PUBLISHER) && !isCreatedByUser(app, principal.getName())) {
            throw new InsufficientPermissionException("Publisher can work with his own Apps only!");
        }
    }
}
