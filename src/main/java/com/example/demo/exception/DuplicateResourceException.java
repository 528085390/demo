package com.example.demo.exception;

//  重复资源异常类
public class DuplicateResourceException extends  RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }

}
