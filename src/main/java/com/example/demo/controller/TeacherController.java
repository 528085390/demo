package com.example.demo.controller;


import com.example.demo.pojo.Result;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.CourseDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private static final String EXPECTED_ROLE = "TEACHER";

    @Autowired
    TeacherService teacherService;
    @Autowired
    AuthService  authService;

    private boolean isAuthorized(HttpServletRequest request, String usernameInPath) {
        String usernameFromJwt = (String) request.getAttribute("usernameFromJwt");
        String roleFromJwt = (String) request.getAttribute("roleFromJwt");

        if (usernameFromJwt == null || roleFromJwt == null || !EXPECTED_ROLE.equals(roleFromJwt)) {
            return false;
        }
        return usernameFromJwt.equals(usernameInPath);
    }
    // add course
    @PostMapping("/{username}/addCourse")
    public Result<Course> addCourse(@PathVariable String username, @RequestBody CourseDTO newCourse, HttpServletRequest request) {
        if(isAuthorized(request, username))   {
            Result<Course> addCourseResult = teacherService.addCourse(username, newCourse);
            return addCourseResult;
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }

    //get all course
    @GetMapping("/{username}/getAllCourse")
    public Result<List<CourseDTO>> getCourse(@PathVariable String username, HttpServletRequest request) {
        if (isAuthorized(request, username)){
            List<CourseDTO> allCourse = teacherService.getAllCourse();
            return Result.success(allCourse);
        }
        return Result.error(Result.FORBIDDEN,"未授权访问或Token无效");
    }






}
