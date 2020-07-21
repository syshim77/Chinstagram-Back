package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.models.User;
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
    public User signUp(@RequestBody User user) {
        return this.userService.signUp(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String logIn(@RequestBody User user) {
        return this.userService.logIn(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateProfile(@RequestBody Profile profile, @PathVariable String id) {
        return this.userService.updateProfile(profile, id);
    }

    @GetMapping("/{id}/profile")    // need to be discussed
    @ResponseStatus(HttpStatus.OK)
    public User getProfile(@PathVariable String id) {
        return this.userService.getProfile(id);
    }
}
