package com.example.demo.controller;

import com.example.demo.dao.ResponseMessage;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.IAdminService;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController  //接口返回对象 转换为json文本
@RequestMapping("/user") // 接口路径
public class AdminController {

    @Autowired
    IAdminService adminService;

    //增加用户
    @PostMapping
    public ResponseMessage<User> add(@RequestBody UserDTO user){
        return ResponseMessage.success(adminService.add(user));
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResponseMessage<User> delete(@PathVariable Long userId){
        User deleteUser = adminService.getUser(userId);
        if (adminService.getUser(userId) == null){
            return ResponseMessage.error();
        }
        else {
            adminService.delete(userId);
            return ResponseMessage.success(deleteUser);
        }

    }

    //查询用户信息 url : /user/userId
    @GetMapping("/{userId}")
    public ResponseMessage<User> get(@PathVariable Long userId){
        if(adminService.getUser(userId) != null){
            return ResponseMessage.success(adminService.getUser(userId));
        }
        else return ResponseMessage.error();

    }

    //修改用户信息
    @PutMapping
    public ResponseMessage<User> update(@RequestBody UserDTO newUser){
        UserDTO rqs = newUser;
        if(adminService.update(rqs.getId(), rqs.getNewUser()) != null){
            User updataUser = adminService.update(rqs.getId(), rqs.getNewUser());
            return ResponseMessage.success(updataUser);
        }
        else return ResponseMessage.error();
    }
}
