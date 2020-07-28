package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.custom.CheckJwt;
import com.landvibe.chinstagram.models.LogInRequest;
import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.models.SignUpResponse;
import com.landvibe.chinstagram.models.User;
import com.landvibe.chinstagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody User user) throws Exception {
        return this.userService.signUp(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String logIn(@RequestBody LogInRequest logInRequest, HttpServletResponse response) throws Exception {
        return this.userService.logIn(logInRequest, response);
    }

    @CheckJwt
    @GetMapping("/{id}/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getProfile(@PathVariable String id) throws Exception {
        return this.userService.getProfile(id);
    }

    @CheckJwt
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateProfile(@RequestParam("image") MultipartFile profileImage, @RequestBody Profile profile, @PathVariable String id) throws Exception {
        return this.userService.updateProfile(profileImage, profile, id);
    }

}
