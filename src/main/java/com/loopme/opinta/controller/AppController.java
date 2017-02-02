package com.loopme.opinta.controller;

import com.loopme.opinta.enums.Role;
import com.loopme.opinta.model.App;
import com.loopme.opinta.service.AppService;
import com.loopme.opinta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/app")
public class AppController {
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    @RequestMapping(value="/list",  method=RequestMethod.GET)
    public ModelAndView listOfStrategies(Principal principal) {
        List<App> apps;
        if (userService.hasRole(principal, Role.ROLE_PUBLISHER)) {
            apps = appService.getByUser(principal.getName());
        } else {
            apps =  appService.getAll();
        }

        ModelAndView modelAndView = new ModelAndView("app-list");
        modelAndView.addObject("apps", apps);
        modelAndView.addObject("app", new App());

        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingStrategy(@ModelAttribute App app, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");
        app.setUser(userService.findByUsername(principal.getName()));
        appService.save(app);
        return modelAndView;
    }

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView editStrategyPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");

        App app = appService.getById(id);

        try {
            appService.canEdit(principal, app);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }

        modelAndView = new ModelAndView("app-edit");
        modelAndView.addObject("app", app);
        return modelAndView;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView editingStrategy(@ModelAttribute App app, @RequestParam(value="action", required=true) String action, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");

        if (action.equals("cancel")) {
            return modelAndView;
        }

        try {
            appService.update(principal, app);
        } catch (Exception e) {
            modelAndView = new ModelAndView("app-edit");
            modelAndView.addObject("app", app);
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(@RequestParam(value="id", required=true) Integer id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/app/list");
        App app = appService.getById(id);

        try {
            appService.delete(principal, app);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
