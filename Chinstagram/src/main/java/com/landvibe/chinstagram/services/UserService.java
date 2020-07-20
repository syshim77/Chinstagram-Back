package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.models.User;
import com.landvibe.chinstagram.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private String access_token;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        return this.userRepository.save(user);
    }

    public String logIn(User user) {
        if (this.userRepository.findById(user.getId()).isPresent()) {
            // TODO: check whether log-in is successful
        } else {
            // TODO: log-in fail
        }
        return access_token;
    }
}
