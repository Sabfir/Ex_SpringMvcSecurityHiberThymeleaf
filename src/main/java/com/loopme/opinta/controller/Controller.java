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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {
    private static String UNKNOWN = "unknown";
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    @RequestMapping(value="/app/list",  method=RequestMethod.GET)
    public ModelAndView listOfStrategies() {
        ModelAndView modelAndView = new ModelAndView("app-list");
        List<App> apps = appService.getAll();
        modelAndView.addObject("apps", apps);
        modelAndView.addObject("app", new App());
        return modelAndView;
    }

    @RequestMapping(value="/app/add", method=RequestMethod.POST)
    public ModelAndView addingStrategy(@ModelAttribute App app) {
        ModelAndView modelAndView = new ModelAndView("redirect:/strategy/list");
        appService.save(app);
        String message = "Strategy " + app.getId() + " was successfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value="/app/edit", method=RequestMethod.GET)
    public ModelAndView editStrategyPage(@RequestParam(value="id", required=true) Integer id) {
        ModelAndView modelAndView = new ModelAndView("app-edit");
        App app = appService.getById(id);
        modelAndView.addObject("app", app);
        return modelAndView;
    }

    @RequestMapping(value="/app/edit", method=RequestMethod.POST)
    public ModelAndView editingStrategy(@ModelAttribute App app,
                                        @RequestParam(value="action", required=true) String action) {

        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");
        String message = null;

        if (action.equals("save")) {
            appService.update(app);
            message = "App " + app.getId() + " was successfully edited";
            modelAndView.addObject("message", message);
        }

        if (action.equals("cancel")) {
            message = "App " + app.getId() + " edit cancelled";
            modelAndView.addObject("message", message);
        }

        return modelAndView;
    }

    @RequestMapping(value="/app/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(@RequestParam(value="id", required=true) Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");
        App app = new App();
        app.setId(id);
        appService.delete(app);
        return modelAndView;
    }





    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        if (principal == null) {
            return "login";
        }

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
//        if (roles.contains(Role.ROLE_OPERATOR.name())) {
//            return "main-operator";
//        } else if (roles.contains(Role.ROLE_PUBLISHER.name())) {
//            return "redirect:/apps";
//        }

        return "redirect:/app/list";
    }









    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public String apps(Model model, Principal principal) {
        if (principal == null) {
            return "login";
        }

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        if (roles.contains(Role.ROLE_PUBLISHER.name())) {
            model.addAttribute("apps", appService.getByUser(principal.getName()));
        } else {
            model.addAttribute("apps", appService.getAll());
        }

        return "apps";
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String app(Model model) {
        App app = new App();
        model.addAttribute("app", app);
        return "app";
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public String createApp(@ModelAttribute("app") App app, Principal principal) {
        if (principal == null) {
            return "login";
        }

        app.setUser(userService.findByUsername(principal.getName()));
        appService.save(app);

        return "redirect:/apps";
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
