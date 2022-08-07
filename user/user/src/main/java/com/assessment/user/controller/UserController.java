package com.assessment.user.controller;

import com.assessment.user.dto.ResponseDto;
import com.assessment.user.dto.UserDto;
import com.assessment.user.entity.UserEntity;
import com.assessment.user.service.IUserService;
import com.assessment.user.util.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.ACCOUNT)
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@RequestBody UserEntity userEntity){

        UserDto userDto = userService.saveUser(userEntity);
        if(userDto.getUserId() == null) {
            return new ResponseEntity<ResponseDto>(new ResponseDto("Error saving user"), HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully saved", userDto), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAllUsers(){
        List<UserDto> userDtos = userService.findAllUsers();
        if(userDtos == null) {
            return new ResponseEntity<>(new ResponseDto("Empty list"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched ", userDtos), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findUserById(@PathVariable("id") Long id){
        UserDto userDto = userService.findByUserId(id);
        if(userDto == null) {
            return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched", userDto), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FIND_ALL_AUTHORITIES_BY_ID)
    public ResponseEntity<ResponseDto> findAllAuthoritiesById(@PathVariable("id") Long id){
        
        List<String> authorities = userService.findAllAuthoritiesByUserId(id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", authorities), HttpStatus.OK);
    }

    @GetMapping(ApiConstant.FIND_USER_ENTITY_WITH_ID)
    public ResponseEntity<ResponseDto> findUsrEntityWithId(@PathVariable("id") Long id){
        UserEntity userEntity = userService.findById(id);
        if(userEntity == null) {
            return new ResponseEntity<>(new ResponseDto("Not found "), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", userEntity), HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ResponseDto>  getUserByIf(@PathVariable("username") String username){
        UserDto userDto = userService.findByUsername(username);
        if(userDto == null){
            return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched ", userDto), HttpStatus.OK);
    }

    @GetMapping("/findUserEntityFromId/{id}")
    public ResponseEntity<ResponseDto> getUserEntityId(@PathVariable("id") Long id){
        UserEntity userEntity = userService.findById(id);
        if(userEntity == null) {
            return new ResponseEntity<>(new ResponseDto("User not found"), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new ResponseDto("Successfully fetched ", userEntity), HttpStatus.OK);
    }
}
