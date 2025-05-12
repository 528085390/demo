package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service // 标识为bean
public class AdminService implements IAdminService{

    @Autowired
    AdminDao adminDao;

    //add
    @Override
    public User add(UserDTO newUser) {
        User addUser = new User();
        BeanUtils.copyProperties(newUser,addUser);
        addUser.setRole("ADMIN");
        addUser.setStatus(1);
        addUser.setCreateTime(LocalDateTime.now());
        addUser.setUpdateTime(LocalDateTime.now());
        return adminDao.save(addUser);
    }

    //delete
    @Override
    public void delete(Long id) {
        adminDao.deleteById(id);
    }

    //find
    @Override
    public User getUser(Long userId) {
        if (adminDao.existsById(userId)){
            return adminDao.findById(userId).get();
        }
        else return null;
    }

    //update
    @Override
    public User update(Long id, User newUser) {
        if (adminDao.existsById(id)){
            User oldUser = adminDao.findById(id).get();
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setRole(newUser.getRole());
            oldUser.setStatus(newUser.getStatus());
            return adminDao.save(oldUser);
        }
        return null;
    }




}
