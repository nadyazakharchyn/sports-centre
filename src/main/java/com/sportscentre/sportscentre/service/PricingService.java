package com.sportscentre.sportscentre.service;

import com.sportscentre.sportscentre.model.Pass;
import com.sportscentre.sportscentre.model.Pricing;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.repository.ActivityRepository;
import com.sportscentre.sportscentre.repository.PassRepository;
import com.sportscentre.sportscentre.repository.PricingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PricingService {
    @Autowired
    private PricingRepository pricingRepository;
    @Autowired
    private PassRepository passRepository;
    @Autowired
    private ActivityRepository activityRepository;
    public List<Pricing> getPricing(String activity, String totalVisits){
        if(activity == null && totalVisits == null) return pricingRepository.findAll();
        if (activity == null) return pricingRepository.findAllByTotalVisits(Long.valueOf(totalVisits));
        if (totalVisits == null) return pricingRepository.findAllByActivity(activity);
        else return pricingRepository.findAllByActivityAndTotalVisits(activity, Long.valueOf(totalVisits));
    }

    public Pricing order (User user, Pricing pricing){
        Pass newPass = new Pass(user, pricing.getTotal_visits(), pricing.getTotal_visits(),pricing.getPrice(),"To be paid",activityRepository.findByName(pricing.getActivity()));
        passRepository.save(newPass);
        return pricing;
    }

    public Optional<Pricing> getById(Long id){
        return pricingRepository.findById(id);
    }

}
