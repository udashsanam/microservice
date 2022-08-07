package com.assessment.authentication.exception;

public class ErrorWhileParsingException extends RuntimeException{
    String message;
    public ErrorWhileParsingException(String message){
        super(message);
    }

    public ErrorWhileParsingException(String message, Exception ex ){
        super(message, ex);
    }
}
