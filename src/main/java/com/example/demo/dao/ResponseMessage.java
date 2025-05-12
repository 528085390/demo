package com.example.demo.dao;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Setter
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage() {

    }

    public static <T>ResponseMessage<T> success(T data){
        ResponseMessage<T> responseMessage = new ResponseMessage<T>();
        responseMessage.setCode(HttpStatus.OK.value()); // 200
        responseMessage.setMessage("success");
        responseMessage.setData(data);
        return responseMessage;
    }

    public static <T>ResponseMessage<T> error(){
        ResponseMessage<T> responseMessage = new ResponseMessage<T>();
        responseMessage.setCode(HttpStatus.NO_CONTENT.value());// 204
        responseMessage.setMessage("no data");
        return responseMessage;
    }

}
