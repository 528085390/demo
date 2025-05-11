package com.example.demo.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO extends BaseUser{
    Long id;
    User newUser;
}
