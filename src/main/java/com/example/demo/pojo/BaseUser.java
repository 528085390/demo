package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// 用户类

@Data
@MappedSuperclass
public abstract class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
