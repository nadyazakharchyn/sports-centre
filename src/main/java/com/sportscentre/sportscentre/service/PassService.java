package com.sportscentre.sportscentre.service;
import com.sportscentre.sportscentre.exception.PassNotFoundException;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.repository.PassRepository;
import com.sportscentre.sportscentre.model.Pass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PassService {
    private final PassRepository passRepository;
    public PassService(PassRepository passRepository){
        this.passRepository = passRepository;
    }

    public List<Pass> getPasses(){
        return passRepository.findAll();
    }

    public Pass savePass(Pass newPass){
        return passRepository.save(newPass);
    }

    public void deleteById(Long id) {
        passRepository.deleteById(id);
    }

    public Pass getPassById(Long id){
        Optional<Pass> pass = passRepository.findById(id);
        if(pass.isPresent()){
            log.info("pass: {}", pass.get());
            return pass.get();
        } throw new PassNotFoundException();
    }

    public List<Pass> getPassByUser(User user){
        return passRepository.findAllByUser(user);
    }

    public Pass updatePassById(Long id, Pass updPass){
        Optional <Pass> pass = passRepository.findById(id);
        if(pass.isPresent()){
            Pass oldPass = pass.get();
            log.info("pass: {}", oldPass);
            updatePass(oldPass, updPass);
            return passRepository.save(oldPass);
        } throw new PassNotFoundException();
    }
    private void updatePass(Pass oldPass, Pass newPass){
        if (!oldPass.getStatus().equals("Inactive")) oldPass.setStatus(newPass.getStatus());
        if (newPass.getVisits_left() >= 0) {
            oldPass.setVisits_left(newPass.getVisits_left());
        } else oldPass.setVisits_left(0L);

    }


}