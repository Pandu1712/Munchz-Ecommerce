package com.auth.auth_service.service;

import com.auth.auth_service.entity.Permission;
import com.auth.auth_service.entity.Role;
import com.auth.auth_service.entity.RolePermission;
import com.auth.auth_service.repository.PermissionRepository;
import com.auth.auth_service.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    public Permission createPermission(String name,String desc){
        Permission p=new Permission();
        p.setName(name);
        p.setDescription(desc);
        return permissionRepository.save(p);
    }

    public void assignPermissionToRole(Integer roleId,Integer perId){
        RolePermission.RolePermissionId id=new RolePermission.RolePermissionId();
        id.setRoleId(roleId);
        id.setPermissionId(perId);

        RolePermission rp=new RolePermission();
        rp.setId(id);
        rolePermissionRepository.save(rp);
    }

    public List<Permission> getPermissionForRole(Integer roleId){
        return rolePermissionRepository.findByIdRoleId(roleId)
                .stream()
                .map(rp->permissionRepository.findById(rp.getId().getPermissionId()).orElse(null))
                .toList();
    }
}
