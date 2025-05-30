package com.example.demo.exception;

//  参数错误异常类
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message)
    {
        super(message);
    }

}
