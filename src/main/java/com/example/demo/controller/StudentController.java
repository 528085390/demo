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
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    AuthService authService;

    private static final String ROLE = "STUDENT";

    //get info
    @GetMapping("/{username}/getInfo")
    public Result<StudentDTO> getInfo(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            return studentService.getInfo(username);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //change info
    @PutMapping("/{username}/changeInfo")
    public Result<StudentDTO> changeInfo(@PathVariable String username, @RequestBody StudentDTO studentDTO) {
        if (authService.checkInfo(username, ROLE)){
            studentService.changeInfo(username, studentDTO);
            return Result.success(studentDTO);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //get course
    @GetMapping("/{username}/getCourse")
    public Result<List<CourseDTO>> getCourse(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            List<CourseDTO> Course = studentService.getCourse(username);
            return Result.success(Course);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    // get all course
    @GetMapping("/{username}/getAllCourse")
    public Result<List<CourseDTO>>getAllCourse(@PathVariable String username) {
        if (authService.checkInfo(username, ROLE)){
            List<CourseDTO> allCourse = studentService.getAllCourse();
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }

    //choose course
    @PutMapping("/{username}/chooseCourse")
    public Result<List<CourseDTO>> chooseCourse(@PathVariable String username, @RequestBody Long courseId) {
        if (authService.checkInfo(username, ROLE)){
            List<CourseDTO> course = studentService.chooseCourse(username, courseId);
            if (course != null){
                return Result.success(course);
            }
            return Result.error(Result.NOT_FOUND,"课程不存在或已选课程");
        }
        return Result.error(Result.FORBIDDEN,"未登录或无权访问");
    }



}
