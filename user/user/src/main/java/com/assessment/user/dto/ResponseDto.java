package com.assessment.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {

    public String message;

    public Object object;

    public ResponseDto(String message) {
        this.message = message;
    }


}