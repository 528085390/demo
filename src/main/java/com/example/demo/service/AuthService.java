package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.pojo.*;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    AuthDao authDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    JwtUtil jwtUtil;


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
                    Teacher newTeacher = new Teacher(authDao.findByUsername(username).getId(), username);
                    teacherDao.save(newTeacher);
                    break;
                }
                case "STUDENT": {
                    Student newStudent = new Student(authDao.findByUsername(username).getId(), username, username);
                    studentDao.save(newStudent);
                    break;
                }
                case "ADMIN": {

                }
            }
            return newUser;
        }
        return null;
    }

    public Result<Map<String, Object>> login(UserDTO loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String role = loginUser.getRole();

        User user = authDao.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password) && user.getRole().equals(role)) {

                user.setUpdateTime(LocalDateTime.now());
                authDao.save(user);

                String no = "";
                String name = "";
                switch (role) {
                    case "TEACHER": {
                        name = teacherDao.findByUserId(user.getId()).getName();
                        no = teacherDao.findByUserId(user.getId()).getTeacherNo();
                        break;
                    }
                    case "STUDENT": {
                        name = studentDao.findByUserId(user.getId()).getName();
                        no = studentDao.findByUserId(user.getId()).getStudentNo();
                        break;
                    }
                }

                String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                Map<String, Object> tokenMap = new HashMap<>();
                tokenMap.put("token", token);

                UserInfo userInfo = new UserInfo();
                userInfo.setId(user.getId());
                userInfo.setRole(user.getRole());
                userInfo.setUsername(user.getUsername());
                userInfo.setName(name);
                userInfo.setNo(no);

                tokenMap.put("userInfo", userInfo);

                return Result.success(tokenMap);

            }
            return Result.error(Result.PARAM_ERROR, "用户名或密码或角色错误");
        }
        return Result.error(Result.PARAM_ERROR, "无此用户");
    }

    public boolean logout(String username) {
        User user = authDao.findByUsername(username);
        if (user != null) {
            if (user.getStatus() == 1) {
                user.setStatus(0);
                user.setUpdateTime(LocalDateTime.now());
                authDao.save(user);
                return true;
            }
        }
        return false;
    }


}
