package com.sportscentre.sportscentre.model;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EnableAutoConfiguration
@Entity
@Table(name = "pass")
@NoArgsConstructor
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "total_visits")
    private Long total_visits;

    @NotNull
    @Column(name = "visits_left")
    private Long visits_left;

    @NotNull
    @Column(name = "price")
    private Long price;

    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Pass(User user, Long total_visits, Long visits_left, Long price, String status, Activity activity) {
        this.user = user;
        this.total_visits = total_visits;
        this.visits_left = visits_left;
        this.price = price;
        this.status = status;
        this.activity = activity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisits_left() {
        return visits_left;
    }

    public void setVisits_left(Long visits_left) {
        this.visits_left = visits_left;
    }

    public Long getTotal_visits() {
        return total_visits;
    }

    public void setTotal_visits(Long total_visits) {
        this.total_visits = total_visits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean checkPayment(){
        return status.equals("To be paid");
    }
}
