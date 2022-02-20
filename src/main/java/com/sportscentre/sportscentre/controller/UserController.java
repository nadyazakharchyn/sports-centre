package com.sportscentre.sportscentre.controller;

import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("users")
    public String getAll(Model model) {
        model.addAttribute("isAuthenticated", true);
        model.addAttribute("isAdmin", true);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("users/{user}")
    public String getById(@PathVariable User user, Model model) {
        model.addAttribute("isAuthenticated", true);
        model.addAttribute("isAdmin", true);
        model.addAttribute("user", user);
        return "user-info";
    }



}
