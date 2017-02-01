package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.App;
import com.loopme.opinta.model.User;
import com.loopme.opinta.service.AppService;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class UserController {
    private static String UNKNOWN = "unknown";
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

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
        userService.delete(user);
        return modelAndView;
    }











//    @RequestMapping(value = "/apps", method = RequestMethod.GET)
//    public String apps(Model model, Principal principal) {
//        if (principal == null) {
//            return "login";
//        }
//
//        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
//                .map(Object::toString)
//                .collect(Collectors.toSet());
//
//        if (roles.contains(Role.ROLE_PUBLISHER.name())) {
//            model.addAttribute("apps", appService.getByUser(principal.getName()));
//        } else {
//            model.addAttribute("apps", appService.getAll());
//        }
//
//        return "apps";
//    }
//
//    @RequestMapping(value = "/app", method = RequestMethod.GET)
//    public String app(Model model) {
//        App app = new App();
//        model.addAttribute("app", app);
//        return "app";
//    }
//
//    @RequestMapping(value = "/app", method = RequestMethod.POST)
//    public String createApp(@ModelAttribute("app") App app, Principal principal) {
//        if (principal == null) {
//            return "login";
//        }
//
//        app.setUser(userService.findByUsername(principal.getName()));
//        appService.save(app);
//
//        return "redirect:/apps";
//    }
//
//    @RequestMapping(value = "/registration/role", method = RequestMethod.GET)
//    public String registration(Model model) {
//        return "registration-role";
//    }
//
//    @RequestMapping(value = "/registration/publisher", method = RequestMethod.GET)
//    public String registrationPublisher(Model model) {
//        User user = new User();
//        user.setRole(Role.ROLE_PUBLISHER);
//        model.addAttribute("user", user);
//
//        return "registration";
//    }
//
//    @RequestMapping(value = "/registration/operator", method = RequestMethod.GET)
//    public String registrationOperator(Model model) {
//        User user = new User();
//        user.setRole(Role.ROLE_OPERATOR);
//        model.addAttribute("user", user);
//
//        return "registration";
//    }
//
//    @RequestMapping(value = "/registration/administrator", method = RequestMethod.GET)
//    public String registrationAdministrator(Model model) {
//        User user = new User();
//        user.setRole(Role.ROLE_ADMINISTRATOR);
//        model.addAttribute("user", user);
//
//        return "registration";
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("user") User user, Principal principal) {
//        userService.save(user);
//
//        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
//                .map(Object::toString)
//                .collect(Collectors.toSet());
//
//        if (roles.contains(Role.ROLE_OPERATOR.name())) {
//            return "main-operator";
//        }
//
//        return "bla";
//    }
}
