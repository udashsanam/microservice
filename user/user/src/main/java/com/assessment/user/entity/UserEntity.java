package com.assessment.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity{

    private String username;

    private String email;

    private String password;

    @OneToOne
    private RoleEntity roleEntity;


}
