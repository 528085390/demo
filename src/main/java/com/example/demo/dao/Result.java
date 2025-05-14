package com.example.demo.dao;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class Result<T> {
    /** 响应码 */
    private Integer code;
    /** 响应消息 */
    private String message;
    /** 响应数据 */
    private T data;
    /** 响应时间戳 */
    private Long timestamp;

    /** 成功响应码 */
    public static final Integer SUCCESS = 200;
    /** 参数错误响应码 */
    public static final Integer PARAM_ERROR = 400;
    /** 未授权响应码 */
    public static final Integer UNAUTHORIZED = 401;
    /** 禁止访问响应码 */
    public static final Integer FORBIDDEN = 403;
    /** 资源不存在响应码 */
    public static final Integer NOT_FOUND = 404;
    /** 服务器错误响应码 */
    public static final Integer ERROR = 500;

    /**
     * 私有构造方法
     */
    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功响应
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * 成功响应
     * @param data 响应数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result2 = new Result<>();
        result2.setCode(SUCCESS);
        result2.setMessage("操作成功");
        result2.setData(data);
        return result2;
    }

    /**
     * 失败响应
     * @param code 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result2 = new Result<>();
        result2.setCode(code);
        result2.setMessage(message);
        return result2;
    }

}

/**
 * 分页查询结果
 * @param <T> 数据类型
 */
class PageResult<T> {
    /** 总记录数 */
    private Long total;
    /** 当前页码 */
    private Integer pageNum;
    /** 每页大小 */
    private Integer pageSize;
    /** 数据列表 */
    private List<T> list;

    // getter setter 方法省略
}



