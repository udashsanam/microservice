package com.assessment.user.service;

import com.assessment.user.entity.RolePermissionEntity;
import com.assessment.user.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionEntity, Long> implements IRolePermissionService {

    private RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository){
        super(rolePermissionRepository);
        this.rolePermissionRepository = rolePermissionRepository;
    }
}
