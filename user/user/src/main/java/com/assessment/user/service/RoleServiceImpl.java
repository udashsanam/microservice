package com.assessment.user.service;

import com.assessment.user.entity.RoleEntity;
import com.assessment.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Long> implements IRoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl( RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }


}
