package com.assesment.blog.exception;

import com.assesment.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto> NotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto("Not found exception"), HttpStatus.NOT_FOUND);
    }
}
