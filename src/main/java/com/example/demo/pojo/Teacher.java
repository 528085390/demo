package com.example.demo.pojo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 教师实体类
 */
@Entity
@Table(name = "t_teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /** 用户ID */
    private Long userId;
    /** 教师工号 */
    private String teacherNo;
    /** 所属院系ID */
    private Long departmentId;
    /** 教师姓名 */
    private String name;
    /** 联系电话 */
    private String phone;
    /** 邮箱 */
    private String email;

    public Teacher(Long userId, String name){
        this.userId = userId;
        this.name = name;
    }

}
