package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IAdminService {
    /**
     * add user
     * @param user
     */
    void add(User user);

    void delete(Long id);

    User get(Long id);

    User update(Long id,User newUser);


}
