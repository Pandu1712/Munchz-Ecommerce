package com.auth.auth_service.controller;

import com.auth.auth_service.dto.LoginOtpRequest;
import com.auth.auth_service.dto.OtpVerifyRequest;
import com.auth.auth_service.dto.RegisterRequest;
import com.auth.auth_service.entity.User;
import com.auth.auth_service.entity.UserOtp;
import com.auth.auth_service.repository.UserRepository;
import com.auth.auth_service.security.JwtProvider;
import com.auth.auth_service.service.OtpService;
import com.auth.auth_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    OtpService otpService;

    @Autowired
    JwtProvider jwtProvider;


    @Autowired
    RoleService roleService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        User user=new User();
        user.setEmail(req.email());
        user.setPhone(req.phone());
        userRepo.save(user);
        otpService.sendOtp(req.email(),"email_verify");
        return " Registred. OTP sent for the verification";

    }

    @PostMapping("/verify-otp")
    public String verify(@RequestBody OtpVerifyRequest req){
        User user=otpService.verifyOtp(req.email(),req.otp(),req.purpose());
        user.setEmailVerified(true);
        userRepo.save(user);
        return "OTP Verified";
    }


    @PostMapping("/login-otp")
    public String sendLoginOtp(@RequestBody LoginOtpRequest req){
        otpService.sendOtp(req.email(),"login");
        return "OTP sent for the login";
    }

    @PostMapping("/login-otp/confirm")
    public Map<String,String> confirmLoginOtp(@RequestBody OtpVerifyRequest req){
        User user=otpService.verifyOtp(req.email(),req.otp(),"login");
        List<String> roles = roleService.getUserRoleNames(user.getId());
        String token=jwtProvider.generateToken(user.getId(),user.getEmail(),roles);
        return Map.of("token",token);
    }
}
