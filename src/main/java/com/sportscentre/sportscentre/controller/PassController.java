package com.sportscentre.sportscentre.controller;
import com.sportscentre.sportscentre.model.Pass;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PassController {
    @Autowired
    private  PassService passService;

    @GetMapping("mypasses")
    public String getUserPasses(@AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        model.addAttribute("passes", passService.getPassByUser(user));
        return "your-passes";
    }

    @GetMapping("passes")
    public String getPasses(@AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        model.addAttribute("passes", passService.getPasses());
        return "passes";
    }

    @GetMapping("mypasses/delete/{id}")
    public String delete(@PathVariable Long id){
        passService.deleteById(id);
        return "redirect:/mypasses";
    }

   /* @PutMapping("/update/passes/{id}")
    public ResponseEntity<Pass> updatePass(@PathVariable(value = "id") Long id, @RequestParam(required = false) Long visits_left, @RequestParam(required = false) String status){

        Pass oldPass = passService.getPassById(id);
        oldPass.setVisits_left(visits_left);
        oldPass.setStatus(status);
        final Pass updatedPass = passService.savePass(oldPass);
        return ResponseEntity.ok(updatedPass);
    }*/
    @GetMapping("passes/edit/{id}")
    public String edit(@PathVariable Long id, @AuthenticationPrincipal User user , Model model){
        checkUser(user, model);
        Pass oldPass = passService.getPassById(id);
        model.addAttribute(oldPass);
        return "edit-pass";
    }

    @PostMapping("passes/edit/{id}")
    public String editPass(@PathVariable Long id, @RequestParam(value = "subtractedVisits",required = false) String substractedVisits,
                           @RequestParam(value = "status", required = false) String status,@AuthenticationPrincipal User user , Model model){
        checkUser(user, model);
        Pass newPass = passService.getPassById(id);
        if (!substractedVisits.equals("")) {
            if(Long.parseLong(substractedVisits)>=newPass.getVisits_left()) newPass.setStatus("Inactive");
            newPass.setVisits_left(newPass.getVisits_left() - Long.parseLong(substractedVisits));
        }
        if (!status.equals("")) newPass.setStatus(status);
        passService.updatePassById(id, newPass);
        model.addAttribute("pass", passService.getPassById(id));

        return "redirect:/passes";
    }



    @PostMapping(value = "passes")
    public ResponseEntity<Pass> postPasses(@Valid @RequestBody Pass newPass){
        return ResponseEntity.ok(passService.savePass(newPass));
    }


    @PutMapping(value = "passes/{id}")
    public ResponseEntity<Pass> updatePass(@PathVariable Long id, @Valid @RequestBody Pass updPass){
        return ResponseEntity.ok(passService.updatePassById(id, updPass));
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
