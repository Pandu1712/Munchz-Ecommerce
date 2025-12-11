package com.user.user_profile_service.controller;

import com.user.user_profile_service.dto.CreateProfileRequest;
import com.user.user_profile_service.entity.Profile;
import com.user.user_profile_service.service.ProfileService;
import com.user.user_profile_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/update")
    public Profile updateProfile(@RequestHeader("Authorization") String token,
                                 @RequestBody CreateProfileRequest req) {
        UUID userId = jwtUtil.extractUserId(token);
        return profileService.createProfile(userId, req);
    }

    @GetMapping
    public Profile getProfile(@RequestHeader("Authorization") String token) {
        UUID userId = jwtUtil.extractUserId(token);
        return profileService.getProfile(userId);
    }


}
