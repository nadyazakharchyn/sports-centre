package com.sportscentre.sportscentre.service;

import com.sportscentre.sportscentre.model.Activity;
import com.sportscentre.sportscentre.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAll(){return activityRepository.findAll();};

    public Activity getByName(String name){
        return activityRepository.findByName(name);
    }
}
