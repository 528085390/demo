package com.example.demo.controller;


import com.example.demo.dao.Result;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private static final String ROLE = "TEACHER";

    @Autowired
    TeacherService teacherService;
    @Autowired
    AuthService  authService;

    // add course
    @RequestMapping("/{username}/course")
    @PostMapping
    public Result<Course> addCourse(@PathVariable String username, @RequestBody CourseDTO newCourse) {
        if(authService.checkInfo(username, ROLE))   {
            Result<Course> courseResult = teacherService.addCourse(username, newCourse);
            return courseResult;
        }
        return Result.error(Result.UNAUTHORIZED,"未登录或无权访问");
    }




}
