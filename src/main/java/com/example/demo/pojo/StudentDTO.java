package com.example.demo.pojo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {

    private String username;
    private String password;
    private String name;
    private String studentNo;

    public StudentDTO() {
    }

    public StudentDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
