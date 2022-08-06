package com.assessment.user.service;

import com.assessment.user.entity.PermissionEntity;
import com.assessment.user.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionEntity, Long> implements IPermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository){
        super(permissionRepository);
        this.permissionRepository = permissionRepository;
    }

}
