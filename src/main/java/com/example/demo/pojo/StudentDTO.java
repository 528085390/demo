package com.example.demo.pojo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

//  学生DTO，用于交互

/**
 * username 用户名
 * password 密码
 * name 姓名
 * studentNo 学号（未启用）
 */
@Data
@AllArgsConstructor
public class StudentDTO {

    private String username;
    private String password;
    private String name;
    private String studentNo;


    public StudentDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
