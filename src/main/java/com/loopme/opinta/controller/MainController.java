package com.loopme.opinta.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private static String UNKNOWN = "unknown";

//    @RequestMapping(value = "/login",  method = RequestMethod.GET)
//    public ModelAndView login() {
//        ModelAndView modelAndView = new ModelAndView("login");
//        modelAndView.addObject("error", "");
//        return modelAndView;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Username or password is incorrect.");
//        }
//        if (logout != null) {
//            model.addAttribute("message", "Logged out successfully.");
//        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
            model.addAttribute("roles", roles);
        } else {
            model.addAttribute("username", UNKNOWN);
        }
        return "welcome";
    }
}
