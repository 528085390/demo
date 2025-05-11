package com.example.demo.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * 教师实体类
 */
@Table(name = "t_teacher")
@Entity
@Data
public class Teacher extends BaseUser {

    private String teacherNo;
    private Long departmentId;
    private String name;
    private String phone;
    private String email;

}
