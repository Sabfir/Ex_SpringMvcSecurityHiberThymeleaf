package com.loopme.opinta.controller;

import com.loopme.opinta.model.User;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/user/list",  method=RequestMethod.GET)
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value="/user/add", method=RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        try {
            userService.save(user, principal.getName());
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.GET)
    public ModelAndView editUserPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");

        User user = userService.getById(id);

        try {
            userService.canEdit(principal, user);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }

        modelAndView = new ModelAndView("user-edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @RequestParam(value="action", required=true) String action, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");

        if (action.equals("cancel")) {
            return modelAndView;
        }

        try {
            userService.update(principal, user);
        } catch (Exception e) {
            modelAndView = new ModelAndView("user-edit");
            modelAndView.addObject("user", user);
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value="/user/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        User user = userService.getById(id);

        try {
            userService.delete(principal, user);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
