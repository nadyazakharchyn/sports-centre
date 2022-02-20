package com.sportscentre.sportscentre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "activities_and_prices")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "activity")
    private String activity;

    @NotNull
    @Column(name = "price")
    private Long price;

    @NotNull
    @Column(name = "total_visits")
    private Long totalVisits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setName(String activity) {
        this.activity = activity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotal_visits() {
        return totalVisits;
    }

    public void setTotal_visits(Long total_visits) {
        this.totalVisits = total_visits;
    }
}
