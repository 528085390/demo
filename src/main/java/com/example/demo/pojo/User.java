package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_user")
public class User extends BaseUser {
    //a
}
