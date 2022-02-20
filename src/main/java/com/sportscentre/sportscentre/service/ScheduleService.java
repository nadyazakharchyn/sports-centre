package com.sportscentre.sportscentre.service;

import com.sportscentre.sportscentre.model.Pass;
import com.sportscentre.sportscentre.model.Schedule;
import com.sportscentre.sportscentre.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedule(){
        return scheduleRepository.findAll();
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule saveScheduleRecord(Schedule scheduleRecord){
        return scheduleRepository.save(scheduleRecord);
    }
}
