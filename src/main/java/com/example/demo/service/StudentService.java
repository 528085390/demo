package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.OperationNotPermittedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.Result;
import com.example.demo.dao.StudentDao;
import com.example.demo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    AuthDao authDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseDao courseDao;

    //  获取个人信息
    public Result<StudentDTO> getInfo(String username) {
        Student findStudent = studentDao.findByUsername(username);
        User findUser = authDao.findByUsername(username);

        //  判断是否存在
        if (findStudent == null || findUser == null) {
            throw new DuplicateResourceException("无此用户");
        }
        StudentDTO student = new StudentDTO(findUser.getUsername(), findUser.getPassword(), findStudent.getName());
        return Result.success(student);
    }

    //  修改个人信息
    public void changeInfo(String username, StudentDTO newStudentDTO) {

        //  参数判断
        if (Objects.equals(newStudentDTO.getName(), "") || Objects.equals(newStudentDTO.getPassword(), "") || Objects.equals(newStudentDTO.getUsername(), "")){
            throw new DuplicateResourceException("参数为空");
        }
        //  用户存在判断
        User user = authDao.findByUsername(username);
        if (user == null){
            throw new ResourceNotFoundException("用户 ‘" + username + "’ 不存在");
        }
        user.setUsername(newStudentDTO.getUsername());
        user.setPassword(newStudentDTO.getPassword());
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(0);
        authDao.save(user);

        Student student = studentDao.findByUsername(username);
        if(student == null){
            throw new ResourceNotFoundException("学生 ‘" + username + "’ 不存在");
        }
        student.setName(newStudentDTO.getName());
        student.setUsername(newStudentDTO.getUsername());
        studentDao.save(student);
    }

    //  获取已选课程
    public List<CourseDTO> getCourse(String username) {
        Student student = studentDao.findByUsername(username);

        //  用户存在判断
        if (student == null) {
            throw new ResourceNotFoundException("学生用户 ‘" + username +"’ 不存在");
        }
        //  从List中获取已选课程
        List<Long> courseIds = student.getCourseIds();
        if (courseIds == null) {
            return null;
        }
        List<Optional<Course>> list = courseIds.stream().map(courseId -> courseDao.findById(courseId)).toList();
        List<Course> Course = list.stream().map(course -> course.get()).toList();
        List<CourseDTO> allCourseDTO = Course.stream().map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getMajorId(), course.getGrade(), course.getCourseType(), course.getCredit(), course.getIsPublic(), course.getStatus())).toList();
        return allCourseDTO;
    }


    // 获取所有课程
    public List<CourseDTO> getAllCourse() {
        Iterable<Course> allCourse = courseDao.findAll();
        //  判断是否有课可以选
        if (allCourse.iterator().next() == null) {
            throw new ResourceNotFoundException("没有可选课程");
        }
        List<Course> allCourseList = (List<Course>) allCourse;
        List<CourseDTO> allCourseDTO = allCourseList.stream().map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getMajorId(), course.getGrade(), course.getCourseType(), course.getCredit(), course.getIsPublic(), course.getStatus())).toList();
        return allCourseDTO;
    }


    //  选课
    public List<CourseDTO> chooseCourse(String username, Long courseId) {
        Student student = studentDao.findByUsername(username);
        //  用户存在判断
        if (student == null){
            throw new ResourceNotFoundException("学生用户" + username + "不存在");
        }
        List<Long> courseIds = student.getCourseIds();

        // 是否已选课程判断
        if (courseIds.contains(courseId)) {
            throw new DuplicateResourceException("该学生已选该课程");
        }

        //  课程存在判断
        if (!courseDao.existsById(courseId)){
            throw new ResourceNotFoundException("课程不存在");
        }

        Course chooseCourse = courseDao.findById(courseId).get();

        //  课程状态判断
        if (!chooseCourse.getIsPublic().equals(1)){
            throw new OperationNotPermittedException("该课程非公开课程");
        }

        courseIds.add(courseId);
        student.setCourseIds(courseIds);
        studentDao.save(student);
        return getCourse(username);
    }
}
