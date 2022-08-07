package com.assessment.authentication.exception;

public class NotSavedException extends RuntimeException{

    String message;

    public NotSavedException(String message){
        super(message);
    }
    public NotSavedException(String message, Exception ex){
        super(message, ex);
    }
}
