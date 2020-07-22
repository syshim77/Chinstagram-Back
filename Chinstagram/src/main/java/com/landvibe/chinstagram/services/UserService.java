package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.*;
import com.landvibe.chinstagram.repositories.UserRepository;
import com.landvibe.chinstagram.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void verifyDuplicatedUser(String id) {
        if (userRepository.findById(id).isPresent())
            throw new IllegalArgumentException("This ID is already exist.");
    }

    public SignUpResponse signUp(User user) {
        verifyDuplicatedUser(user.getId());

        user.setToken(jwtUtil.createToken());
        userRepository.save(user);

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return signUpResponse;
    }

    public String logIn(LogInRequest logInRequest) {
        User findUser = userRepository.findById(logInRequest.getId()).orElseThrow(() -> new IllegalArgumentException("This ID is not exist."));

        if (!findUser.getPw().equals(logInRequest.getPw()))
            throw new IllegalArgumentException("Password is not correct.");

        return findUser.getToken();
    }

    public User getProfile(String id) {
        return this.userRepository.findById(id).get();
    }

    public User updateProfile(Profile profile, String id) {
        User loginUser = userRepository.findById(id).get();
        loginUser.setProfile(profile);

        return this.userRepository.save(loginUser);
    }

}
