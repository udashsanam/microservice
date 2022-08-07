package com.assessment.authentication.controller;

import com.assessment.authentication.exception.NotFoundException;
import com.assessment.authentication.model.AuthenticationRequestDto;
import com.assessment.authentication.model.ResponseDto;
import com.assessment.authentication.model.UserDto;
import com.assessment.authentication.model.UserEntity;
import com.assessment.authentication.security.JwtToken;
import com.assessment.authentication.service.BlogUserDetailServiceImpl;
import com.assessment.authentication.util.BlogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private RestTemplate restTemplate;

    private JwtToken jwtToken;

    private PasswordEncoder passwordEncoder;

    private BlogUserDetailServiceImpl blogUserDetailService;

    public AuthenticationController(RestTemplate restTemplate,
                                    JwtToken jwtToken,
                                    PasswordEncoder passwordEncoder,
                                    BlogUserDetailServiceImpl blogUserDetailService){

        this.restTemplate = restTemplate;
        this.jwtToken = jwtToken;
        this.passwordEncoder = passwordEncoder;
        this.blogUserDetailService = blogUserDetailService;
    }


    @PostMapping
    public void test(){

      Object o =   restTemplate.getForObject("http://localhost:8082/api/user/account/findAll", Object.class);
      System.out.println(o.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto = new ResponseDto();
        String testObject="";
        try {
            testObject = objectMapper.writeValueAsString(o);
        }catch (Exception ex){
            throw new RuntimeException();
        }

        try {
           responseDto = objectMapper.readValue(testObject, ResponseDto.class);
//            PharmedicaResponseDto pharmedicaResponseDto = objectMapper.readValue(testObject, PharmedicaResponseDto.class);
//            System.out.println(pharmedicaResponseDto);
            List<UserDto> userDtos = new ArrayList<>();
            List<UserDto> userDtoList = objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), userDtos.getClass());
            System.out.println(userDtoList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        if(authenticationRequestDto.getUsername() == null || authenticationRequestDto.getPassword() == null) throw new NotFoundException("Username password not found");

        ResponseEntity<ResponseDto> responseDtoResponseEntity =null;
        try {
//            object= restTemplate.exchange("http://localhost:8082/api/user/account/findByUsername/"+ authenticationRequestDto.getUsername(), HttpMethod.GET,null, Object.class);

            responseDtoResponseEntity =  restTemplate.exchange("http://user-service/api/user/account/findByUsername/"+ authenticationRequestDto.getUsername(), HttpMethod.GET,null, ResponseDto.class);
        } catch (HttpStatusCodeException exception){
            return new ResponseEntity<>(new ResponseDto("User not found "), HttpStatus.NO_CONTENT);
        }

        UserDto userDto = new UserDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseDto responseDto = BlogUtil.getResponseDto(responseDtoResponseEntity.getBody());
            userDto = objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), userDto.getClass());
            responseDtoResponseEntity = restTemplate.exchange("http://user-service/api/user/account/findUserEntityFromId/"+userDto.getUserId(), HttpMethod.GET,null, ResponseDto.class);
            responseDto = BlogUtil.getResponseDto(responseDtoResponseEntity.getBody());
            UserEntity userEntity= objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), UserEntity.class);

//            if(!checkPassword(authenticationRequestDto.getPassword(), userEntity.getPassword())){
//             return new ResponseEntity<>(new ResponseDto("Password did not match "), HttpStatus.BAD_REQUEST);
//            }


        } catch (HttpStatusCodeException  e){
            return new ResponseEntity<>(new ResponseDto("User not found"), e.getStatusCode());
        }  catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // generating jwt toke n
    UserDetails userDetails = blogUserDetailService.loadUserByUsername(userDto.getUsername());

        String token = jwtToken.generateToken(userDetails);
        return new ResponseEntity<>(new ResponseDto("Sucessfully login in", token), HttpStatus.OK);
    }

    private boolean checkPassword(String rawPassword, String password){

        return passwordEncoder.matches(rawPassword, password);
    }

}
