package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDao extends CrudRepository<User, Long> {
    User findByUsername(String username);

}


