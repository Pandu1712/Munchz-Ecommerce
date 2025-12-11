package com.user.user_profile_service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;


    public UUID extractUserId(String authHeader){
        String token=authHeader.replace("Bearer ","");

        return UUID.fromString(
                JWT.require(Algorithm.HMAC256(secret))
                        .build()
                        .verify(token)
                        .getSubject()
        );

    }
}
