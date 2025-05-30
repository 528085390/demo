package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

//用户登陆注册登出
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    //登录
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody UserDTO loginUser) {
        return authService.login(loginUser);
    }

    //注册
    @PostMapping("/register")
    public Result<User> register(@RequestBody UserDTO registerUser) {
        User user = authService.register(registerUser);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error(Result.PARAM_ERROR, "用户名已存在");
    }

    //登出
    @PutMapping("/{username}/logout")
    public Result<User> logout(@PathVariable String username) {
        authService.logout(username);
        return Result.success();
    }


}
