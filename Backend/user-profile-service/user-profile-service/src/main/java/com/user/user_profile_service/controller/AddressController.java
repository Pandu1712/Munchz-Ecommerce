package com.user.user_profile_service.controller;

import com.user.user_profile_service.dto.CreateAddressRequest;
import com.user.user_profile_service.entity.Address;
import com.user.user_profile_service.service.AddressService;
import com.user.user_profile_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/add")
    public Address add(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateAddressRequest req
    ) {
        UUID userId = jwtUtil.extractUserId(token);
        return addressService.addAddress(userId, req);
    }

    @GetMapping("/list")
    public List<Address> list(@RequestHeader("Authorization") String token) {
        UUID userId = jwtUtil.extractUserId(token);
        return addressService.listAddresses(userId);
    }
}

