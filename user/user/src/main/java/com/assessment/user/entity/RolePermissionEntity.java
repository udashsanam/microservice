package com.assessment.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "role_permission")
public class RolePermissionEntity  extends BaseEntity{

    @ManyToOne
    private RoleEntity roleEntity;

    @ManyToOne
    private PermissionEntity permissionEntity;

}
