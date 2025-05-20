package com.example.demo.pojo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String role;

}
