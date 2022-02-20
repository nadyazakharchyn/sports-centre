package com.sportscentre.sportscentre.controller;

import com.sportscentre.sportscentre.model.Activity;
import com.sportscentre.sportscentre.model.Schedule;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal User user,
                           Model model){
        checkUser(user, model);
        List<Activity> activities = activityService.getAll();
        model.addAttribute("activities",activities);
        return "main";
    }

    private void checkUser(@AuthenticationPrincipal User cUser, Model model) {

        boolean isAuthenticated = false;
        boolean isAdmin = false;
        if(cUser != null) {
            isAuthenticated = true;
            if (cUser.isAdmin())
                isAdmin = true;
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("isAdmin", isAdmin);
    }
}
