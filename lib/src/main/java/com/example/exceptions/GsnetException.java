package com.example.exceptions;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class GsnetException extends Exception {

    private ErrorCode errorCode;
    public ErrorCode getErrorCode(){
        return  errorCode;
    }

    public GsnetException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GsnetException(ErrorCode errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }
}
