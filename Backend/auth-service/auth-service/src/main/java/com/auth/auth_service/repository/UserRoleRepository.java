package com.auth.auth_service.repository;

import com.auth.auth_service.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole,UserRole.UserRoleId> {
    List<UserRole> findByIdUserId(UUID userId);


    @Query("SELECT r.name FROM Role r JOIN UserRole ur ON ur.id.roleId = r.id WHERE ur.id.userId = :userId")
    List<String> findRolesByUserId(UUID userId);


}
