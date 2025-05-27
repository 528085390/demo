package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;

import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController  //接口返回对象 转换为json文本
@RequestMapping("/user") // 接口路径
public class AdminController {


    AdminService adminService;

    //增加用户
    @PostMapping
    public Result<User> add(@RequestBody UserDTO user){
        return Result.success(adminService.add(user));
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public Result<User> delete(@PathVariable Long userId){
        User deleteUser = adminService.getUser(userId);
        if (adminService.getUser(userId) == null){
            return Result.error(Result.NOT_FOUND,"用户不存在");
        }
        else {
            adminService.delete(userId);
            return Result.success(deleteUser);
        }

    }

    //查询用户信息 url : /user/userId
    @GetMapping("/{userId}")
    public Result<User> get(@PathVariable Long userId){
        if(adminService.getUser(userId) != null){
            return Result.success(adminService.getUser(userId));
        }
        else return Result.error(Result.NOT_FOUND,"用户不存在");

    }

    //修改用户信息
    @PutMapping("/{userId}")
    public Result<User> update(@PathVariable Long userId, @RequestBody UserDTO newUser){
        if(adminService.update(userId,newUser) != null){
            User updataUser = adminService.update(userId,newUser);
            return Result.success(updataUser);
        }
        else return Result.error(Result.NOT_FOUND,"用户不存在");
    }
}
