package com.example.demo.service;

import com.example.demo.dao.UserMeth;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service // 标识为bean
public class AdminService implements IAdminService{

    @Autowired
    UserMeth userMeth;

    //add
    @Override
    public void add(User user) {
        user.setRole("ADMIN");
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMeth.save(user);
    }

    //delete
    @Override
    public void delete(Long id) {
        userMeth.deleteById(id);
    }

    //find
    @Override
    public User get(Long id) {
        return userMeth.findById(id).get();
    }

    //update
    @Override
    public User update(Long id, User newUser) {
        if (userMeth.existsById(id)){
            User oldUser = userMeth.findById(id).get();
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setRole(newUser.getRole());
            oldUser.setStatus(newUser.getStatus());
            return userMeth.save(oldUser);
        }
        return null;
    }




}
