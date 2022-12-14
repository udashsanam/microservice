package com.assessment.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity{

    @Column(name = "username", unique = true)
    private String username;

    private String email;

    private String password;

    @OneToOne
    private RoleEntity roleEntity;


}
