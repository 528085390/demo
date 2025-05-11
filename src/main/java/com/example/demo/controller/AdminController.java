package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.IAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController  //接口返回对象 转换为json文本
@RequestMapping("/user") // 接口路径
public class AdminController {

    @Autowired
    IAdminService adminService;


    //增加用户
    @PostMapping
    public User add(@RequestBody User user){
        adminService.add(user);
        return user;
    }

    //删除用户
    @DeleteMapping
    public String delete(@RequestParam Long id){
        adminService.delete(id);
        return "delete success";
    }


    //查询用户信息
    @GetMapping
    public User getInfo(@RequestParam Long id){
        return adminService.get(id);
    }

    //修改用户信息
    @PutMapping
    public User update(@RequestBody UserDTO newUser){
        UserDTO rqs = newUser;
        return adminService.update(rqs.getId(), rqs.getNewUser());
    }


}
