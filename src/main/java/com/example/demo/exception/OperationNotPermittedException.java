package com.example.demo.exception;

// 操作不允许异常类
public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
