package com.example.demo.exception;

import com.example.demo.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//  全局异常处理
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public Result<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return Result.error(Result.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseBody
    public Result<Object> handleDuplicateResourceException(DuplicateResourceException ex) {
        return Result.error(Result.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    public Result<Object> handleInvalidInputException(InvalidInputException ex) {
        return Result.error(Result.PARAM_ERROR, ex.getMessage());
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    @ResponseBody
    public Result<Object> handleOperationNotPermittedException(OperationNotPermittedException ex) {
        return Result.error(Result.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result<Object> handleAuthenticationException(AuthenticationException ex) {
        return Result.error(Result.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handleGlobalException() {
        return Result.error(Result.ERROR, "服务器内部错误");
    }
}