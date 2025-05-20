package com.example.demo.pojo;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "t_student")
@NoArgsConstructor
public class Student extends BaseUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long userId;
    private String name;
    private String studentNo;
    private List<Long> courseIds;


    public Student(Long userId, String username, String name) {
        this.userId = userId;
        this.username = username;
        this.name = name;
    }

}
