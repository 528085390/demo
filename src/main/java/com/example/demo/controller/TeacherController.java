package com.example.demo.controller;


import com.example.demo.dao.Result;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.AuthService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PostMapping("/{username}/addCourse")
    public Result<Course> addCourse(@PathVariable String username, @RequestBody CourseDTO newCourse) {
        if(authService.checkInfo(username, ROLE))   {
            Result<Course> addCourseResult = teacherService.addCourse(username, newCourse);
            return addCourseResult;
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //get all course
    @GetMapping("/{username}/allCourse")
    public Result<List<CourseDTO>> getCourse(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            List<CourseDTO> allCourse = teacherService.getAllCourse();
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }






}
