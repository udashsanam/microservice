package com.assessment.authentication.exception;

import com.assessment.authentication.model.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto> NotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NotSavedException.class)
    public ResponseEntity<ResponseDto> NotSavedException(NotSavedException ex){

        return new ResponseEntity<>(new ResponseDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CannotDeleteException.class)
    public ResponseEntity<ResponseDto> CannotDeleteException(CannotDeleteException ex){
        return new ResponseEntity<>(new ResponseDto(ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = ErrorWhileParsingException.class)
    public ResponseEntity<ResponseDto> errorParsingException(ErrorWhileParsingException exception){
        return new ResponseEntity<>(new ResponseDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
