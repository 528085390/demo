package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    AuthDao authDao;
    @Autowired
    TeacherDao teacherDao;


    public User register(UserDTO registerUser) {
        String username = registerUser.getUsername();
        String password = registerUser.getPassword();
        String role = registerUser.getRole();

        if (authDao.findByUsername(username) == null) {
            LocalDateTime createTime = LocalDateTime.now();
            LocalDateTime updateTime = LocalDateTime.now();
            Integer status = 0;
            User newUser = new User(username, password, role, status, createTime, updateTime);
            authDao.save(newUser);
            switch (role) {
                case "TEACHER": {
                    Teacher newTeacher = new Teacher(authDao.findByUsername(username).getId(), createTime, updateTime);
                    teacherDao.save(newTeacher);
                }
                case "STUDENT": {

                }
                case "ADMIN": {

                }
            }
            return newUser;
        }
        return null;
    }

    public User login(UserDTO loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String role = loginUser.getRole();

        if (authDao.findByUsername(username) != null) {
            User user = authDao.findByUsername(username);
            if (user.getPassword().equals(password) && user.getRole().equals(role)) {
                if (user.getStatus() == 0) {
                    user.setStatus(1);
                    user.setUpdateTime(LocalDateTime.now());
                    authDao.save(user);
                } else {
                    user.setUsername("1");
                }
                return user;
            }
            user.setUsername("-1");
            return user;
        }
        return null;
    }

    public boolean logout(String username) {
        if(authDao.findByUsername(username) != null){
            User user = authDao.findByUsername(username);
            if(user.getStatus() == 1){
                user.setStatus(0);
                user.setUpdateTime(LocalDateTime.now());
                authDao.save(user);
                return true;
            }
        }
        return false;
    }

}
