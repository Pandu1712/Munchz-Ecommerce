package com.auth.auth_service.dto;

import java.util.UUID;

public record AssignRoleRequest(UUID userId, Integer roleId, UUID assignedBy) {
}
