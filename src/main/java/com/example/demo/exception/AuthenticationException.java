package com.example.demo.exception;

//  用户认证异常
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
