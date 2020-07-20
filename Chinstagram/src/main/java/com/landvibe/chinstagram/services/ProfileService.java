package com.landvibe.chinstagram.services;

import com.landvibe.chinstagram.models.Profile;
import com.landvibe.chinstagram.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile updateProfile(Profile profile, int id) {
        return this.profileRepository.save(profile);
    }
}
