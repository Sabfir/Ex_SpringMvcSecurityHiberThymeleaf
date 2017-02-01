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
    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    @PostConstruct
    public void init() {
        populateClients();
    }

    public void populateClients() {
//        Role roleUser = new Role();
//        roleUser.setName("ROLE_USER");
//        Role roleAdmin = new Role();
//        roleAdmin.setName("ROLE_ADMIN");
//        roleService.save(roleUser);
//        roleService.save(roleAdmin);
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("test");
//        user.setRoles(Stream.of(roleAdmin, roleUser).collect(Collectors.toSet()));

        User user = new User();
        user.setUsername("p");
        user.setPassword("p");
        user.setRole(Role.ROLE_PUBLISHER);
        userService.save(user);

        user = new User();
        user.setUsername("o");
        user.setPassword("o");
        user.setRole(Role.ROLE_OPERATOR);
        userService.save(user);

        user = new User();
        user.setUsername("a");
        user.setPassword("a");
        user.setRole(Role.ROLE_ADMINISTRATOR);
        userService.save(user);

        App app = new App();
        app.setName("Add for IOS");
        app.setAppType(AppType.IOS);
        app.setUser(userService.findByUsername("p"));
        app.setContentTypes(Stream.of(ContentType.VIDEO, ContentType.IMAGE).collect(Collectors.toSet()));
        appService.save(app);

        app = new App();
        app.setName("Add for WEBSITE");
        app.setAppType(AppType.WEBSITE);
        app.setUser(userService.findByUsername("o"));
        app.setContentTypes(Stream.of(ContentType.HTML).collect(Collectors.toSet()));
        appService.save(app);

        app = new App();
        app.setName("Add for Android");
        app.setAppType(AppType.ANDROID);
        app.setUser(userService.findByUsername("a"));
        appService.save(app);
    }
}
