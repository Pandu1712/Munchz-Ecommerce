package com.auth.auth_service.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @EmbeddedId
    private RolePermissionId id;

    @Embeddable
    @Data
    public static class RolePermissionId implements Serializable {
        private Integer roleId;
        private Integer permissionId;
    }
}
