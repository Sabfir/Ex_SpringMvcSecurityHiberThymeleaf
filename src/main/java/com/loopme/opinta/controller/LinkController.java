package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LinkController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_ADMINISTRATOR.name())) {
            return "redirect:/user/list";
        }

        return "redirect:/app/list";
    }
}
