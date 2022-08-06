package com.assessment.user.service;

import com.assessment.user.dto.UserDto;
import com.assessment.user.entity.UserEntity;

import java.util.List;

public interface IUserService extends IBaseService<UserEntity, Long> {


    UserDto saveUser(UserEntity userEntity);

    List<UserDto> findAllUsers();

    UserDto findByUserId(Long id);

    List<String> findAllAuthoritiesByUserId(Long id);
}
