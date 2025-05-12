package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // spring bean
public interface AdminDao extends CrudRepository<User, Long> {

}
