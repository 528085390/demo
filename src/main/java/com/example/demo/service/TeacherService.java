package com.example.demo.service;

import com.example.demo.dao.AuthDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.pojo.*;
import com.example.demo.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;
    @Autowired
    AuthDao authDao;
    @Autowired
    CourseDao courseDao;

    public Result<Course> addCourse(String username, CourseDTO newCourse) {
        if (courseDao.existsCourseByCourseName(newCourse.getCourseName())) {
            throw new DuplicateResourceException("课程名称：" + newCourse.getCourseName() + "已存在");
        }
        Long id = authDao.findByUsername(username).getId();
        Teacher teacher = teacherDao.findByUserId(id);
        Long teacherId = teacher.getId();

        long nextId = courseDao.count() + 1;
        String courseNo = "CS" + String.format("%03d", nextId);

        Course course = new Course();
        course.setCourseNo(courseNo);
        course.setCourseName(newCourse.getCourseName());
        course.setTeacherId(teacherId);
        course.setMajorId(newCourse.getMajorId());
        course.setGrade(newCourse.getGrade());
        course.setCourseType(newCourse.getCourseType());
        course.setCredit(newCourse.getCredit());
        course.setIsPublic(newCourse.getIsPublic());
        course.setStatus(1);
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());

        courseDao.save(course);
        return Result.success(course);

    }


    public List<CourseDTO> getAllCourse() {
        Iterable<Course> findAllCourse = courseDao.findAll();
        List<Course> allCourse = (List<Course>) findAllCourse;
        List<CourseDTO> listCourse = allCourse.stream().map(course -> new CourseDTO(course.getId(), course.getCourseName(), course.getMajorId(), course.getGrade(), course.getCourseType(), course.getCredit(), course.getIsPublic(), course.getStatus())).toList();
        return listCourse;

    }
}
