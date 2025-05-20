package com.example.demo.controller;

import com.example.demo.dao.Result;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.pojo.StudentDTO;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    AuthService authService;

    private static final String ROLE = "STUDENT";

    //get info
    @RequestMapping("/{username}/getInfo")
    @GetMapping
    public Result<StudentDTO> getInfo(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            return studentService.getInfo(username);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //change info
    @RequestMapping("/{username}/changeInfo")
    @PutMapping
    public Result<StudentDTO> changeInfo(@PathVariable String username, StudentDTO studentDTO) {
        if (authService.checkInfo(username, ROLE)){
            studentService.changeInfo(username, studentDTO);
            return Result.success(studentDTO);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //get course
    @RequestMapping("/{username}/getCourse")
    @GetMapping
    public Result<List<CourseDTO>> getCourse(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            List<CourseDTO> allCourse = studentService.getCourse(username);
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }
}
