package com.sportscentre.sportscentre.model;

import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@EnableAutoConfiguration
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "weekday")
    private String weekday;

    @NotNull
    @Column(name = "start_time")
    private Time start_time;

    @NotNull
    @Column(name = "end_time")
    private Time end_time;

    @NotNull
    @Column(name = "hall")
    private String hall;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Schedule(String weekday, Time start_time, Time end_time, String hall, Activity activity) {
        this.weekday = weekday;
        this.start_time = start_time;
        this.end_time = end_time;
        this.hall = hall;
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity_id(Activity activity) {
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    }
