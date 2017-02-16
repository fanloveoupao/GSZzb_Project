package com.example.exceptions;

/**
 * Created by fan-gk on 2017/2/3.
 */


public class NetworkException extends Exception {
    public NetworkException(String message) {
        super(message, new GsnetException(ErrorCode.NETWORK, message));
    }
    public NetworkException(String message, Throwable throwable) {
        super(message, new GsnetException(ErrorCode.NETWORK, message, throwable));
    }
}