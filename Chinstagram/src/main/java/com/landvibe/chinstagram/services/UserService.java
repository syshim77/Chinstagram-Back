package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.jwt.JwtService;
import com.landvibe.chinstagram.models.*;
import com.landvibe.chinstagram.repositories.ImageRepository;
import com.landvibe.chinstagram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void verifyDuplicatedUser(String id) throws AuthenticationException {
        if (userRepository.findById(id).isPresent())
            throw new AuthenticationException("This ID is already exist.");
    }

    public SignUpResponse signUp(User user) throws Exception {
        verifyDuplicatedUser(user.getId());

        userRepository.save(user);

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return signUpResponse;
    }

    public String logIn(LogInRequest logInRequest, HttpServletResponse response) throws Exception {
        User findUser = userRepository.findById(logInRequest.getId()).orElseThrow(() -> new AuthenticationException("This ID is not exist."));

        if (!findUser.getPw().equals(logInRequest.getPw()))
            throw new AuthenticationException("Password is not correct.");

        String token = null;
        try {
            token = jwtService.createToken("loginUser", logInRequest);
        } catch(Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setHeader("Authorization", token);
        findUser.setToken(token);
        userRepository.save(findUser);

        return token;
    }

    public User getProfile(String id) throws Exception {
        if (!userRepository.findById(id).isPresent()) {
            throw new AuthenticationException("This ID is not exist.");
        }

        return this.userRepository.findById(id).get();
    }

    public User updateProfile(MultipartFile profileImage, Profile profile, String id) throws Exception {
        User loginUser = userRepository.findById(id).orElseThrow(() -> new AuthenticationException("This ID is not exist."));
        loginUser.setProfile(profile);

        return this.userRepository.save(loginUser);
    }
}
