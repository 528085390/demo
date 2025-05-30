package com.example.demo.controller;


import com.example.demo.pojo.Result;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  老师控制器
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    AuthService authService;

    private static final String EXPECTED_ROLE = "TEACHER";

    // 辅助方法，用于检查JWT提供的角色和用户名是否匹配路径变量且是否登录
    private boolean isAuthorized(HttpServletRequest request, String usernameInPath) {
        String usernameFromJwt = (String) request.getAttribute("usernameFromJwt");
        String roleFromJwt = (String) request.getAttribute("roleFromJwt");

        if (usernameFromJwt == null || !EXPECTED_ROLE.equals(roleFromJwt) || !authService.isExists(usernameFromJwt)) {
            return false;
        }
        if (!authService.isLogin(usernameFromJwt)){
            return false;
        }
        return usernameFromJwt.equals(usernameInPath);
    }

    //  添加课程
    @PostMapping("/{username}/addCourse")
    public Result<Course> addCourse(@PathVariable String username, @RequestBody CourseDTO newCourse, HttpServletRequest request) {
        if (isAuthorized(request, username)) {
            Result<Course> addCourseResult = teacherService.addCourse(username, newCourse);
            return addCourseResult;
        }
        return Result.error(Result.FORBIDDEN, "未授权访问或Token无效");
    }

    //  获取所有课程
    @GetMapping("/{username}/getAllCourse")
    public Result<List<CourseDTO>> getCourse(@PathVariable String username, HttpServletRequest request) {
        if (isAuthorized(request, username)) {
            List<CourseDTO> allCourse = teacherService.getAllCourse();
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN, "未授权访问或Token无效");
    }


}
