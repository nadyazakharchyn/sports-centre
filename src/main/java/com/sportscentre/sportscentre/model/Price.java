package com.sportscentre.sportscentre.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "total_visits")
    private Long total_visits;

    @NotNull
    @Column(name = "price")
    private Long priceValue;

    //@OneToMany(mappedBy = "price", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Pass> passes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal_visits() {
        return total_visits;
    }

    public void setTotal_visits(Long total_visits) {
        this.total_visits = total_visits;
    }

    public Long getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Long priceValue) {
        this.priceValue = priceValue;
    }

    //public List<Pass> getPasses() {
      //  return passes;
    //}

    //public void setPasses(List<Pass> passes) {
      //  this.passes = passes;
    //}
}
