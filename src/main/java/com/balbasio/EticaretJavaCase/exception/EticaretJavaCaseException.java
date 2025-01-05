package com.balbasio.EticaretJavaCase.exception;

import lombok.Getter;

@Getter
public class EticaretJavaCaseException extends RuntimeException{
    private final ErrorType errorType;

    public EticaretJavaCaseException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public EticaretJavaCaseException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
