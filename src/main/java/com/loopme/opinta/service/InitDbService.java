package com.loopme.opinta.service;

import com.loopme.opinta.model.Role;
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
    private RoleService roleService;

    @PostConstruct
    public void init() {
        populateClients();
    }

    public void populateClients() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.save(roleUser);
        roleService.save(roleAdmin);

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRoles(Stream.of(roleAdmin, roleUser).collect(Collectors.toSet()));

        userService.save(user);
    }
}
