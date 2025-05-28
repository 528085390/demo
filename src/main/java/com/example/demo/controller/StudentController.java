package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.pojo.StudentDTO;
import com.example.demo.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    private static final String EXPECTED_ROLE = "STUDENT";

    // 辅助方法，用于检查JWT提供的角色和用户名是否匹配路径变量
    private boolean isAuthorized(HttpServletRequest request, String usernameInPath) {
        String usernameFromJwt = (String) request.getAttribute("usernameFromJwt");
        String roleFromJwt = (String) request.getAttribute("roleFromJwt");

        // 检查JWT是否提供了用户名和角色，并且角色是期望的STUDENT角色
        if (usernameFromJwt == null || roleFromJwt == null || !EXPECTED_ROLE.equals(roleFromJwt)) {
            return false;
        }
        // 检查JWT中的用户名是否与路径中的用户名匹配 (安全性考虑，学生只能操作自己的信息)
        return usernameFromJwt.equals(usernameInPath);
    }

    //get info
    @GetMapping("/{username}")
    public Result<StudentDTO> getInfo(@PathVariable String username, HttpServletRequest request) {
        if (isAuthorized(request, username)){
            return studentService.getInfo(username);
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

    //change info
    @PutMapping("/{username}")
    public Result<StudentDTO> changeInfo(@PathVariable String username, @RequestBody StudentDTO studentDTO, HttpServletRequest request) {
        if (isAuthorized(request, username)){
            studentService.changeInfo(username, studentDTO);
            return Result.success(studentDTO);
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

    //get course
    @GetMapping("/{username}/getCourse")
    public Result<List<CourseDTO>> getCourse(@PathVariable String username, HttpServletRequest request) {
        if (isAuthorized(request, username)){
            List<CourseDTO> Course = studentService.getCourse(username);
            return Result.success(Course);
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

    // get all course
    @GetMapping("/{username}/getAllCourse")
    public Result<List<CourseDTO>>getAllCourse(HttpServletRequest request) {
        String roleFromJwt = (String) request.getAttribute("roleFromJwt");
        if (roleFromJwt != null && roleFromJwt.equals(EXPECTED_ROLE)){
            List<CourseDTO> allCourse = studentService.getAllCourse();
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

    //choose course
    @PutMapping("/{username}/chooseCourse")
    public Result<List<CourseDTO>> chooseCourse(@PathVariable String username, @RequestBody Long courseId, HttpServletRequest request) {
        if (isAuthorized(request, username)){
            String usernameFromJwt = (String) request.getAttribute("usernameFromJwt");
            List<CourseDTO> course = studentService.chooseCourse(usernameFromJwt, courseId);
            if (course != null){
                return Result.success(course);
            }
            return Result.error(Result.PARAM_ERROR,"不可选此课");
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

}
