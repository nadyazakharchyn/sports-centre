package com.sportscentre.sportscentre.repository;

import com.sportscentre.sportscentre.model.Activity;
import com.sportscentre.sportscentre.model.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {
    List<Pricing> findAllByActivity(String activity);
    List<Pricing> findAllByTotalVisits(Long totalVisits);
    List<Pricing> findAllByActivityAndTotalVisits(String activity, Long totalVisits);

}
