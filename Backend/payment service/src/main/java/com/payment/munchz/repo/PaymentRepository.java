package com.payment.munchz.repo;




import com.payment.munchz.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByRazorpayOrderId(String razorpayOrderId);
}
