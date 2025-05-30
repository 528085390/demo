package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//  用户表

/**
 * id 用户表里的唯一标识
 * username 用户名
 * password 密码
 * role 角色
 * status 登录状态（0 未登录， 1 已登录）
 * createTime 创建时间
 * updateTime 更新时间
 */
@Data
@Entity
@Table(name = "t_user")
@NoArgsConstructor
public class User extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public User(String username, String password, String role, Integer status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
