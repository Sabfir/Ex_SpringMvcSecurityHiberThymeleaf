package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.App;
import com.loopme.opinta.service.AppService;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AppController {
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    @RequestMapping(value="/app/list",  method=RequestMethod.GET)
    public ModelAndView listOfStrategies(Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }

        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        List<App> apps;
        if (roles.contains(Role.ROLE_PUBLISHER.name())) {
            apps = appService.getByUser(principal.getName());
        } else {
            apps =  appService.getAll();
        }

        ModelAndView modelAndView = new ModelAndView("app-list");
        modelAndView.addObject("apps", apps);
        modelAndView.addObject("app", new App());

        return modelAndView;
    }

    @RequestMapping(value="/app/add", method=RequestMethod.POST)
    public ModelAndView addingStrategy(@ModelAttribute App app, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");
        app.setUser(userService.findByUsername(principal.getName()));
        appService.save(app);
        String message = "Strategy " + app.getId() + " was successfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value="/app/edit", method=RequestMethod.GET)
    public ModelAndView editStrategyPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:/login");
        }
        Set<String> roles = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        if (roles.contains(Role.ROLE_PUBLISHER.name())) {
            if (!appService.isCreatedByUser(id, principal.getName())) {
                throw new IllegalArgumentException("Publisher can edit his own Apps only!");
            }
        }

        ModelAndView modelAndView = new ModelAndView("app-edit");
        App app = appService.getById(id);
        modelAndView.addObject("app", app);
        return modelAndView;
    }

    @RequestMapping(value="/app/edit", method=RequestMethod.POST)
    public ModelAndView editingStrategy(@ModelAttribute App app,
                                        @RequestParam(value="action", required=true) String action) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");

        if (action.equals("cancel")) {
            return modelAndView;
        } else if (action.equals("save")) {
            appService.update(app);
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
}
