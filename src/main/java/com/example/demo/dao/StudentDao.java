package com.example.demo.dao;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {
    Student findByUsername(String username);

    Student findByUserId(Long userId);
}
