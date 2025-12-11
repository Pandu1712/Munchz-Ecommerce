package com.auth.auth_service.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_roles")
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    private UUID assignedBy;
    private Instant assignedAt;

    @Data
    @Embeddable
    public static class UserRoleId implements Serializable {
        private UUID userId;
        private Integer roleId;
    }


}
