package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.exception.AuthenticationException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.InvalidInputException;
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

        if (authDao.findByUsername(username) != null) {
            throw new DuplicateResourceException("用户名 '" + username + "' 已存在");
        }
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();
        Integer status = 0;
        User newUser = new User(username, password, role, status, createTime, updateTime);
        authDao.save(newUser);
        User saveUser = authDao.findByUsername(username);

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
            default: {
                throw new InvalidInputException("用户角色无效");
            }
        }
        return saveUser;
    }

    public Result<Map<String, Object>> login(UserDTO loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String role = loginUser.getRole();

        User user = authDao.findByUsername(username);

        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("用户名或密码错误");
        }
        if (!user.getRole().equals(role)) {
            throw new AuthenticationException("角色不匹配");
        }

        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(1);
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

    public void logout(String username) {
        User user = authDao.findByUsername(username);
        if (user != null) {
            if (user.getStatus() == 1) {
                user.setStatus(0);
                user.setUpdateTime(LocalDateTime.now());
                authDao.save(user);
                return;
            }
            throw new AuthenticationException("用户未登录");
        }
        throw new AuthenticationException("没有此用户");
    }

    public Boolean isLogin(String username){
        if (authDao.findByUsername(username).getStatus() == 1){
            return true;
        }
        else return false;
    }
}
