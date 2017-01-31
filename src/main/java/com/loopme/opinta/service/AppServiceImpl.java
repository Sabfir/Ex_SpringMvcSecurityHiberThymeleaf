package com.loopme.opinta.service;

import com.loopme.opinta.dao.AppDao;
import com.loopme.opinta.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppDao appDao;

    @Override
    @Transactional
    public void save(App app) {
        appDao.save(app);
    }
}
