package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.models.*;
import com.landvibe.chinstagram.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

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
    public String logIn(@RequestBody LogInRequest logInRequest) throws Exception {
        return this.userService.logIn(logInRequest);
    }

    @GetMapping("/{id}/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getProfile(@PathVariable String id) throws Exception {
        return this.userService.getProfile(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateProfile(@RequestBody Profile profile, @PathVariable String id) throws Exception {
        return this.userService.updateProfile(profile, id);
    }

}
