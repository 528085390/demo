package com.example.demo.pojo;

import lombok.*;

import java.time.LocalDateTime;


//  用户DTO，用于登录注册

/**
 * username 用户名
 * password 密码
 * role 角色
 */
@Data
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String role;

}
