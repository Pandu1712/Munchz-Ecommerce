package com.auth.auth_service.dto;

public record OtpVerifyRequest(String email,String otp,String purpose) {
}
