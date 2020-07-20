package com.landvibe.chinstagram.controllers;

import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Profile updateProfile(@RequestBody Profile profile, @PathVariable int id) {
        return this.profileService.updateProfile(profile, id);
    }
}
