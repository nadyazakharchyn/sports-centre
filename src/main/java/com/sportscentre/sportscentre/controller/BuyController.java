package com.sportscentre.sportscentre.controller;

import com.sportscentre.sportscentre.model.Activity;
import com.sportscentre.sportscentre.model.Pricing;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.service.ActivityService;
import com.sportscentre.sportscentre.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BuyController {
    @Autowired
    private PricingService pricingService;

    @GetMapping("buy")
    public String getByActivity(@RequestParam(required = false) String activity_name,
                                @RequestParam(required = false) String total_visits,
                                @AuthenticationPrincipal User user,
                                Model model){
        checkUser(user, model);
        if (activity_name != null){
            if (activity_name.equals("-")) activity_name = null;
        }
        if (total_visits != null){
            if (total_visits.equals("-")) total_visits = null;
        }
        List<Pricing> pricings = pricingService.getPricing(activity_name, total_visits);
        model.addAttribute("pricings", pricings);
        return "buy";
    }

    @GetMapping("buy/{pricing}")
    public String order(@PathVariable(value = "pricing") Pricing pricing, @AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        pricingService.order(user,pricing);
        return "redirect:/mypasses";
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
