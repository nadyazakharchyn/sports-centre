package com.sportscentre.sportscentre.controller;

import com.sportscentre.sportscentre.model.Pricing;
import com.sportscentre.sportscentre.model.Schedule;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.service.ActivityService;
import com.sportscentre.sportscentre.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.List;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;
    private ActivityService activityService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String mainPagewithSchedule(@AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        List<Schedule> schedules = scheduleService.getSchedule();
        model.addAttribute("schedules", schedules);
        return "schedule";
    }
    @GetMapping("schedule/delete/{id}")
    public String delete(@PathVariable Long id){
        scheduleService.deleteById(id);
        return "redirect:/schedule";
    }

    @GetMapping("schedule/add")
    public String add(@AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        return "add-schedule-record";
    }

    @PostMapping("schedule/add/")
    public String addNew(@RequestParam(value = "activityName") String activityName,
                        @RequestParam(value = "weekday") String weekday,
                        @RequestParam(value = "startTime") Time start_time,
                        @RequestParam(value = "endTime") Time end_time,
                        @RequestParam(value = "hall") String hall,
                        @AuthenticationPrincipal User user, Model model){
        checkUser(user, model);
        Schedule schedule_record = new Schedule(weekday,start_time,end_time,hall,activityService.getByName(activityName));
        scheduleService.saveScheduleRecord(schedule_record);
        return "redirect:/schedule";
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
