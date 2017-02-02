package com.loopme.opinta.service;

import com.loopme.opinta.enums.AppType;
import com.loopme.opinta.enums.ContentType;
import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InitDbService {
//    private static String CRED_PUBLISHER = "Publisher Ivanov";
//    private static String CRED_OPERATOR = "Operator Petrov";
//    private static String CRED_ADMINISTRATOR = "Admin Sidorov";
    private static String CRED_PUBLISHER = "p";
    private static String CRED_OPERATOR = "o";
    private static String CRED_ADMINISTRATOR = "a";
    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    @PostConstruct
    public void init() {
        populateClients();
    }

    public void populateClients() {
        User user = new User();
        user.setUsername(CRED_PUBLISHER);
        user.setPassword(CRED_PUBLISHER);
        user.setRole(Role.ROLE_PUBLISHER);
        userService.save(user);

        user = new User();
        user.setUsername(CRED_OPERATOR);
        user.setPassword(CRED_OPERATOR);
        user.setRole(Role.ROLE_OPERATOR);
        userService.save(user);

        user = new User();
        user.setUsername(CRED_ADMINISTRATOR);
        user.setPassword(CRED_ADMINISTRATOR);
        user.setRole(Role.ROLE_ADMINISTRATOR);
        userService.save(user);

        App app = new App();
        app.setName("Add for IOS");
        app.setAppType(AppType.IOS);
        app.setUser(userService.findByUsername(CRED_PUBLISHER));
        app.setContentTypes(Stream.of(ContentType.VIDEO, ContentType.IMAGE).collect(Collectors.toSet()));
        appService.save(app);

        app = new App();
        app.setName("Add for WEBSITE");
        app.setAppType(AppType.WEBSITE);
        app.setUser(userService.findByUsername(CRED_OPERATOR));
        app.setContentTypes(Stream.of(ContentType.HTML).collect(Collectors.toSet()));
        appService.save(app);

        app = new App();
        app.setName("Add for Android");
        app.setAppType(AppType.ANDROID);
        app.setUser(userService.findByUsername(CRED_ADMINISTRATOR));
        appService.save(app);
    }
}
