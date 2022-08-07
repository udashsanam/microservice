package com.assessment.user.service;

import com.assessment.user.dto.UserDto;
import com.assessment.user.entity.RoleEntity;
import com.assessment.user.entity.RolePermissionEntity;
import com.assessment.user.entity.UserEntity;
import com.assessment.user.exception.NotFoundException;
import com.assessment.user.exception.NotSavedException;
import com.assessment.user.repository.RolePermissionRepository;
import com.assessment.user.repository.RoleRepository;
import com.assessment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements IUserService {

    public UserRepository userRepository;

    public RoleRepository roleRepository;

    public RolePermissionRepository rolePermissionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RolePermissionRepository rolePermissionRepository,
                           RoleRepository roleRepository){
        super(userRepository);
        this.userRepository = userRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDto saveUser(UserEntity userEntity) {
        RoleEntity roleEntity = roleRepository.findById(userEntity.getRoleEntity().getId()).orElse(null);
        if(roleEntity == null) throw new NotFoundException("Role not found");
        if(userEntity.getUsername() == null || userEntity.getPassword() == null) throw new NotFoundException("username or paswword not found ");
        try {
            userEntity = userRepository.save(userEntity);
        }catch (Exception ex){
            throw new NotSavedException("user could  not saved");
        }


        return new UserDto(userEntity.getUsername(), userEntity.getEmail(), userEntity.getId(), userEntity.getRoleEntity().getName());
    }

    @Override
    public List<UserDto> findAllUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();
        if(userEntityList == null || userEntityList.size() == 0) return null;

        List<UserDto> userDtos =  new ArrayList<>();

        userEntityList.forEach(userEntity -> userDtos.add(new UserDto(userEntity.getUsername(),
                userEntity.getEmail(), userEntity.getId(), userEntity.getRoleEntity().getName())));

        return userDtos;

    }

    @Override
    public UserDto findByUserId(Long id){

        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity == null) return null;
        return new UserDto(userEntity.getUsername(), userEntity.getEmail(), userEntity.getId(), userEntity.getRoleEntity().getName());

    }

    @Override
    public List<String> findAllAuthoritiesByUserId(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity == null) throw new NotFoundException("user with id " + id + "not found");
        if(userEntity.getRoleEntity() == null) throw new NotFoundException("Role of the user not found");
        List<String> authorities = new ArrayList<>();
        List<RolePermissionEntity> rolePermissionEntities = rolePermissionRepository.findAllByRoleEntityId(userEntity.getRoleEntity().getId());
        authorities.add("ROLE_" + userEntity.getRoleEntity().getName());

        if(rolePermissionEntities != null || rolePermissionEntities.size() != 0){
            for (RolePermissionEntity rolePermissionEntity:
                    rolePermissionEntities) {
                authorities.add(rolePermissionEntity.getPermissionEntity().getName().toUpperCase());
            }
        }


        return authorities;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) return null;
        return new UserDto(userEntity.getUsername(), userEntity.getEmail(), userEntity.getId(), userEntity.getRoleEntity().getName());
    }
}
