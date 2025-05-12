package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IAdminService {

    User add(UserDTO newUser);

    void delete(Long id);

    User update(Long id,User newUser);

    User getUser(Long userId);
}
