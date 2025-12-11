package com.auth.auth_service.repository;

import com.auth.auth_service.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission,RolePermission.RolePermissionId> {
    List<RolePermission> findByIdRoleId(Integer roleId);
}
