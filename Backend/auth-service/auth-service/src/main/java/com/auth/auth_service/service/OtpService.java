package com.auth.auth_service.service;

import com.auth.auth_service.entity.User;
import com.auth.auth_service.entity.UserOtp;
import com.auth.auth_service.repository.UserOtpRepository;
import com.auth.auth_service.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOtpRepository userOtpRepository;


    public void sendOtp(String email,String purpose){

        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));

        String otp=String.valueOf(new Random().nextInt(900000)+100000);
        String hash= DigestUtils.sha256Hex(otp);
        UserOtp otpRecord=new UserOtp();
        otpRecord.setUserId(user.getId());
        otpRecord.setOtpHash(hash);
        otpRecord.setPurpose(purpose);
        otpRecord.setExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES));  // REQUIRED
        otpRecord.setConsumed(false);

        userOtpRepository.save(otpRecord);
        emailService.sendOtpMail(email,otp);
    }

    public User verifyOtp(String email,String otp,String purpose){

        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user email not found"));
        UserOtp otpRec=userOtpRepository.findByUserIdAndPurposeAndConsumedFalse(user.getId(),purpose)
                .orElseThrow(()->new RuntimeException("invalid otp"));

   if(Instant.now().isAfter(otpRec.getExpiresAt()))
       throw  new RuntimeException("OTP expired");

   if(!DigestUtils.sha256Hex(otp).equals(otpRec.getOtpHash()))
       throw new RuntimeException("invalid Otp");

   otpRec.setConsumed(true);
        userOtpRepository.save(otpRec);
        return user;
    }
}
