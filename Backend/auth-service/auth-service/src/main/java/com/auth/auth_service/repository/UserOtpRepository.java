package com.auth.auth_service.repository;

import com.auth.auth_service.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, UUID> {
    Optional<UserOtp> findAllByUserIdAndPurposeAndConsumedFalseOrderByExpiresAtDesc(UUID userId,String purpose);
    void deleteByUserIdAndPurpose(UUID userId, String purpose);

}
