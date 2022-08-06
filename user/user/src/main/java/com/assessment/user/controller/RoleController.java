package com.assessment.user.controller;

import com.assessment.user.dto.ResponseDto;
import com.assessment.user.entity.RoleEntity;
import com.assessment.user.exception.NotSavedException;
import com.assessment.user.service.IRoleService;
import com.assessment.user.util.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.ROLE)
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveRole(@RequestBody RoleEntity roleEntity){
        try{
            roleEntity = roleService.save(roleEntity);
        } catch (Exception ex) {
            throw new NotSavedException("Error saving roles");
        }
        if(roleEntity.getId() == null) {
            return new ResponseEntity<>(new ResponseDto("Role not saved"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto("successfuly saved", roleEntity), HttpStatus.OK);
    }

}
