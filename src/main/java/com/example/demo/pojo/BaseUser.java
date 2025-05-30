package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 用户基类

/**
 * id  用户id
 * username  用户名
 * password  密码
 * role  角色
 * status  登录状态（0为登出，1为登录，主要是为了实现登出功能）
 * createTime  创建时间
 * updateTime  更新时间
 */
@Data
public abstract class BaseUser {

    private Long id;
    private String username;
    private String password;
    private String role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
