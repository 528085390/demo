package com.example.demo.dao;

import com.example.demo.pojo.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherDao extends CrudRepository<Teacher, Long> {
}
