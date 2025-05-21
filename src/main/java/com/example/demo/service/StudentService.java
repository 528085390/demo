package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.dao.Result;
import com.example.demo.dao.StudentDao;
import com.example.demo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    AuthDao authDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseDao courseDao;

    public Result<StudentDTO> getInfo(String username) {
        Student findStudent = studentDao.findByUsername(username);
        if (findStudent != null) {
            StudentDTO student = new StudentDTO(findStudent.getUsername(), findStudent.getPassword(), findStudent.getName());
            return Result.success(student);
        }
        return Result.error(Result.PARAM_ERROR, "无此用户");

    }

    public void changeInfo(String username, StudentDTO newStudentDTO) {
        User user = authDao.findByUsername(username);
        user.setUsername(newStudentDTO.getUsername());
        user.setPassword(newStudentDTO.getPassword());
        user.setUpdateTime(LocalDateTime.now());
        authDao.save(user);
        Student student = studentDao.findByUsername(username);
        student.setName(newStudentDTO.getName());
        student.setUsername(newStudentDTO.getUsername());
        student.setPassword(newStudentDTO.getPassword());
        studentDao.save(student);
    }

    public List<CourseDTO> getCourse(String username) {
        Student student = studentDao.findByUsername(username);
        List<Long> courseIds = student.getCourseIds();
        if (courseIds == null){
            return null;
        }
        List<Optional<Course>> list = courseIds.stream().map(courseId -> courseDao.findById(courseId)).toList();
        List<Course> Course = list.stream().map(course -> course.get()).toList();
        List<CourseDTO> allCourseDTO = Course.stream().map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getMajorId(), course.getGrade(), course.getCourseType(), course.getCredit(), course.getIsPublic(), course.getStatus())).toList();
        return allCourseDTO;
    }

    public List<CourseDTO> getAllCourse() {
        Iterable<Course> allCourse = courseDao.findAll();
        if(allCourse.iterator().next() == null){
            return null;
        }
        List<Course> allCourseList = (List<Course>)allCourse;
        List<CourseDTO> allCourseDTO = allCourseList.stream().map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getMajorId(), course.getGrade(), course.getCourseType(), course.getCredit(), course.getIsPublic(), course.getStatus())).toList();
        return allCourseDTO;
    }

    public List<CourseDTO> chooseCourse(String username, Long courseId) {
        Student student = studentDao.findByUsername(username);
        List<Long> courseIds = student.getCourseIds();
        if (courseIds == null){
            courseIds = new ArrayList<>();
        }
        if(courseIds.contains(courseId)){
            return null;
        }
        if(courseDao.findById(courseId).isPresent()){
            courseIds.add(courseId);
            student.setCourseIds(courseIds);
            studentDao.save(student);
            return getCourse(username);
        }
        return null;
    }
}
