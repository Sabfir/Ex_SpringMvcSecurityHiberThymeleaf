package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.User;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/user/list",  method=RequestMethod.GET)
    public ModelAndView listOfUsers(Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value="/user/add", method=RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            if (user.getRole().equals(Role.ROLE_ADMINISTRATOR) || user.getRole().equals(Role.ROLE_OPERATOR)) {
                modelAndView.addObject("error", "Can't create " + user.getRole() + ". Operator can create only Publishers");
                return modelAndView;
            }
        }
        userService.save(user);

        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.GET)
    public ModelAndView editUserPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            User user = userService.getById(id);
            if (!user.getRole().equals(Role.ROLE_PUBLISHER)) {
                modelAndView.addObject("error", "Can't edit " + user.getRole() + ". Operator can edit only Publishers");
                return modelAndView;
            }
        }

        modelAndView = new ModelAndView("user-edit");
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @RequestParam(value="action", required=true) String action, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        if (action.equals("cancel")) {
            return modelAndView;
        }

        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            if (!user.getRole().equals(Role.ROLE_PUBLISHER)) {
                modelAndView = new ModelAndView("user-edit");
                modelAndView.addObject("user", user);
                modelAndView.addObject("error", "Can't edit " + user.getRole() + ". Operator can edit only Publishers");
                return modelAndView;
            }
        }

        userService.update(user);
        return modelAndView;
    }

    @RequestMapping(value="/user/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_OPERATOR.name())) {
            User user = userService.getById(id);
            if (!user.getRole().equals(Role.ROLE_PUBLISHER)) {
                modelAndView.addObject("error", "Can't delete " + user.getRole() + ". Operator can delete only Publishers");
                return modelAndView;
            }
        }

        User user = new User();
        user.setId(id);
        try {
            userService.delete(user);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
