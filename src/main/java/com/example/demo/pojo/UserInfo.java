package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//  用户信息类，用于交互

/**
 * id 用户表里的id
 * role 角色
 * username 用户名
 * name 姓名
 * No 学生/教师编号（未启用）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String role;
    private String username;
    private String name;
    private String No;
}
