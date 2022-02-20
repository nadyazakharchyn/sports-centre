package com.sportscentre.sportscentre.controller;

import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.repository.UserRepository;
import com.sportscentre.sportscentre.service.RoleService;
import com.sportscentre.sportscentre.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Controller
public class RegistrationController {

    private static final String DEFAULT_ROLE = "USER";
    //private RoleService roleService;
    //@Autowired
    //private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal User user,
                               Model model){
        checkUser(user,model);

        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "first_name") String first_name,
                          @RequestParam(value = "last_name") String last_name,
                          @RequestParam(value = "email") String email,
                          @RequestParam(value = "birthdate") String birthdate, Model model){

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = null;
        try {
            parsed = formatter.parse(birthdate);
        } catch (ParseException e) {
            model.addAttribute("dateMessage", "Please, enter correct birth date");
        }

        User newUser = new User(username, password, first_name, last_name, parsed,email);
        if(!userService.isUnique(username)){
            model.addAttribute("message", "Oops! User already exists");
            return "registration";
        }
        newUser.getRoles().add(roleService.getRoleByCode(DEFAULT_ROLE));
        userService.add(username, password, first_name, last_name, email, parsed);

        return "`redirect:/`login";
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
