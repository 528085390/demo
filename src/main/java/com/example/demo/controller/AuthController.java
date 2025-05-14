package com.example.demo.controller;

import com.example.demo.dao.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping("/login")
    @PostMapping
    public Result<User> login(@RequestBody UserDTO loginUser) {
        User user = authService.login(loginUser);
        if(user != null){
            return switch (user.getUsername()) {
                case "1" -> Result.error(Result.PARAM_ERROR, "已登录");
                case "-1" -> Result.error(Result.PARAM_ERROR, "用户名或密码或角色错误");
                default -> Result.success(user);
            };
        }
        else return Result.error(Result.PARAM_ERROR,"无此用户");
    }

    @RequestMapping("/register")
    @PostMapping
    public Result<User> register(@RequestBody UserDTO registerUser) {
        User user = authService.register(registerUser);
        if (user != null){
            return Result.success(user);
        }
        return Result.error(Result.PARAM_ERROR,"用户名已存在");
    }

    @RequestMapping("/{username}/logout")
    @PutMapping
    public Result<User> logout(@PathVariable String username) {
        if(authService.logout(username)){
            return Result.success();
        }
        return Result.error(Result.FORBIDDEN,"无法登出");
    }


}
