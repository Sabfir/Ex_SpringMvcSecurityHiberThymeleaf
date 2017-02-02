package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class LinkController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Principal principal) {
        if (userService.hasRole(principal, Role.ROLE_ADMINISTRATOR)) {
            return "redirect:/user/list";
        }

        return "redirect:/app/list";
    }
}
