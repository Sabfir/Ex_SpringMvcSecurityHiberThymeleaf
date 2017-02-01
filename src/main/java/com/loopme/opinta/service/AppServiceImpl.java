package com.loopme.opinta.service;

import com.loopme.opinta.dao.AppDao;
import com.loopme.opinta.dao.UserDao;
import com.loopme.opinta.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppDao appDao;
    @Autowired
    private UserDao userDao;

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
        return appDao.getByUser(userDao.findByUsername(username));
    }

    @Override
    @Transactional
    public void save(App app) {
        appDao.save(app);
    }

    @Override
    @Transactional
    public void update(App app) {
        appDao.update(app);
    }

    @Override
    @Transactional
    public void delete(App app) {
        appDao.delete(app);
    }
}
