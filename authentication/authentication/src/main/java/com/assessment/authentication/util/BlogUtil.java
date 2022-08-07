package com.assessment.authentication.util;

import com.assessment.authentication.exception.ErrorWhileParsingException;
import com.assessment.authentication.model.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlogUtil {

    public static ResponseDto getResponseDto(Object object){

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto = new ResponseDto();
        try {
            String responseString = objectMapper.writeValueAsString(object);
            responseDto = objectMapper.readValue(responseString, ResponseDto.class);
        } catch (Exception exception){
            throw new ErrorWhileParsingException("error while parsing to response dto ");
        }

        return responseDto;
    }
}
