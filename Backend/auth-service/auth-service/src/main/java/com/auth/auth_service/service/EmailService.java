package com.auth.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpMail(String to,String otp){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setTo(to);
        msg.setText("Your Otp Code");
        msg.setText("Your OTP is: " + otp + "\nValid for 10 minutes.");
        javaMailSender.send(msg);
    }
}
