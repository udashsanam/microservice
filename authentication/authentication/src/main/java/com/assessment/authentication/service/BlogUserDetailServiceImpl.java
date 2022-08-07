package com.assessment.authentication.service;

import com.assessment.authentication.exception.ErrorWhileParsingException;
import com.assessment.authentication.exception.NotFoundException;
import com.assessment.authentication.model.BlogUserDetail;
import com.assessment.authentication.model.ResponseDto;
import com.assessment.authentication.model.UserDto;
import com.assessment.authentication.model.UserEntity;
import com.assessment.authentication.util.BlogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//@RibbonClient(name = "ping-a-server",
//configuration = RibbonConfiguration.class)
public class BlogUserDetailServiceImpl implements UserDetailsService {

    private RestTemplate restTemplate;

    public BlogUserDetailServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        ResponseEntity<ResponseDto> object = null;
        UserDto userDto = new UserDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            object= restTemplate.exchange("http://user-service/api/user/account/findByUsername/"+ username, HttpMethod.GET,null, ResponseDto.class);
            ResponseDto responseDto = BlogUtil.getResponseDto(object.getBody());
            userDto = objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), userDto.getClass());

        } catch (HttpStatusCodeException exception){
            throw new NotFoundException("user not found");
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        List<String> authorities = new ArrayList<>();
        try {
            object = restTemplate.exchange("http://user-service/api/user/account/findAllAuthoritiesById/" + userDto.getUserId(), HttpMethod.GET, null, ResponseDto.class);
            ResponseDto responseDto = BlogUtil.getResponseDto(object.getBody());
          authorities = objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), authorities.getClass());
            object = restTemplate.exchange("http://user-service/api/user/account/findUserEntityFromId/"+userDto.getUserId(), HttpMethod.GET,null, ResponseDto.class);
            responseDto = BlogUtil.getResponseDto(object.getBody());
            userEntity= objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getObject()), UserEntity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (HttpStatusCodeException exception){
            throw new NotFoundException("Authorities not found ");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String s :
                authorities) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(s));

        }


        return new BlogUserDetail(username, userEntity.getPassword(), simpleGrantedAuthorities);
    }

}
