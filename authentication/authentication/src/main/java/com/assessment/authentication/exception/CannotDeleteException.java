package com.assessment.authentication.exception;

public class CannotDeleteException extends RuntimeException{

    private String message;
    public CannotDeleteException(String message){
        super(message);
    }
}
