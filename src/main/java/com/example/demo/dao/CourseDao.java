package com.example.demo.dao;

import com.example.demo.pojo.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository<Course, Long> {

    boolean existsCourseByCourseName(String courseName);
    long count();

}
