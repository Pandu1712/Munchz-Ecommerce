package com.auth.auth_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name="user_otps")
public class UserOtp {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private String otpHash;
    private String purpose;
    private Instant expiresAt;
    private boolean consumed;


}
