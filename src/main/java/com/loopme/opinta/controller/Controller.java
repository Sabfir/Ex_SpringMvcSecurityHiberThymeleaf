package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;
import com.loopme.opinta.service.AppService;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {
    private static String UNKNOWN = "unknown";
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        if (principal == null) {
            return "login";
        }

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            return "main-operator";
        } else if (roles.contains(Role.ROLE_PUBLISHER.name())) {
            return "main-publisher";
        }

        return "bla";
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String app(Model model) {
        App app = new App();
        model.addAttribute("app", app);
        return "app";
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public String createApp(@ModelAttribute("app") App app, Principal principal) {
        app.setUser(userService.findByUsername(principal.getName()));
        appService.save(app);

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        if (roles.contains(Role.ROLE_PUBLISHER.name())) {
            return "main-publisher";
        }

        return "bla";
    }

    @RequestMapping(value = "/registration/role", method = RequestMethod.GET)
    public String registration(Model model) {
        return "registration-role";
    }

    @RequestMapping(value = "/registration/publisher", method = RequestMethod.GET)
    public String registrationPublisher(Model model) {
        User user = new User();
        user.setRole(Role.ROLE_PUBLISHER);
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration/operator", method = RequestMethod.GET)
    public String registrationOperator(Model model) {
        User user = new User();
        user.setRole(Role.ROLE_OPERATOR);
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration/administrator", method = RequestMethod.GET)
    public String registrationAdministrator(Model model) {
        User user = new User();
        user.setRole(Role.ROLE_ADMINISTRATOR);
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, Principal principal) {
        userService.save(user);

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            return "main-operator";
        }

        return "bla";
    }
}
