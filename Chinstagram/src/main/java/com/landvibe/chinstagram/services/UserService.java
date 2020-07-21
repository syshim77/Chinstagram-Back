package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.models.User;
import com.landvibe.chinstagram.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<User> loginUser = userRepository.findById(user.getId());

        if (!loginUser.isPresent()) {
            // TODO: log-in fail
        }

        return access_token;
    }

    public User updateProfile(Profile profile, String id) {
        User loginUser = userRepository.findById(id).get();
        loginUser.setProfile(profile);

        return this.userRepository.save(loginUser);
    }

    public User getProfile(String id) {
        return this.userRepository.findById(id).get();
    }
}
