package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.*;
import com.landvibe.chinstagram.repositories.UserRepository;
import com.landvibe.chinstagram.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void verifyDuplicatedUser(String id) throws AuthenticationException {
        if (userRepository.findById(id).isPresent())
            throw new AuthenticationException("This ID is already exist.");
    }

    public SignUpResponse signUp(User user) throws AuthenticationException {
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

    public String logIn(LogInRequest logInRequest) throws AuthenticationException {
        User findUser = userRepository.findById(logInRequest.getId()).orElseThrow(() -> new AuthenticationException("This ID is not exist."));

        if (!findUser.getPw().equals(logInRequest.getPw()))
            throw new AuthenticationException("Password is not correct.");

        return findUser.getToken();
    }

    public User getProfile(String id) throws AuthenticationException {
        if (!userRepository.findById(id).isPresent()) {
            throw new AuthenticationException("This ID is not exist.");
        }

        return this.userRepository.findById(id).get();
    }

    public User updateProfile(Profile profile, String id) throws AuthenticationException {
        User loginUser = userRepository.findById(id).orElseThrow(() -> new AuthenticationException("This ID is not exist."));
        loginUser.setProfile(profile);

        return this.userRepository.save(loginUser);
    }
}
