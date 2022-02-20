package com.sportscentre.sportscentre.service;

import com.sportscentre.sportscentre.model.Role;
import com.sportscentre.sportscentre.model.User;
import com.sportscentre.sportscentre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User add(String username, String password, String first_name, String last_name, String email, Date birthDate) {
        User user = new User(username, passwordEncoder.encode(password), first_name, last_name, birthDate, email);
        return userRepository.save(user);
    }


    public User add(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User changeRoles(Long id, List<Role> roles) {
        User user = userRepository.findById(id).get();
        user.getRoles().clear();
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public boolean isUnique(String username) {
        return userRepository.findByUsername(username) == null;
    }

}
