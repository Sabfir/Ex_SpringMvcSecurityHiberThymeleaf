package com.loopme.opinta.controller;

import com.loopme.opinta.model.User;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        userService.save(user);
        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.GET)
    public ModelAndView editUserPage(@RequestParam(value="id", required=true) Integer id) {
        ModelAndView modelAndView = new ModelAndView("user-edit");
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/user/edit", method=RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user,
                                        @RequestParam(value="action", required=true) String action) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        if (action.equals("cancel")) {
            return modelAndView;
        } else if (action.equals("save")) {
            userService.update(user);
        }
        return modelAndView;
    }

    @RequestMapping(value="/user/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(@RequestParam(value="id", required=true) Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
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
