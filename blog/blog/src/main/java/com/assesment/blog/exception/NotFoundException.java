package com.assesment.blog.exception;


public class NotFoundException extends Exception{

    public NotFoundException(String  message){
        super(message);
    }

    public NotFoundException(String message, Exception ex){
        super(message, ex);
    }
}
