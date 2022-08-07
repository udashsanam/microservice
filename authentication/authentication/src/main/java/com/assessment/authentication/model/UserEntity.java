package com.assessment.authentication.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEntity extends BaseEntity{

    private String username;

    private String email;

    private String password;

    private RoleEntity roleEntity;


}
