package com.assessment.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    public String message;

    public Object object;

    public ResponseDto(String message) {
        this.message = message;
    }


}