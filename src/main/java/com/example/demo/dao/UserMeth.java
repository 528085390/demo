package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // spring bean
public interface UserMeth extends CrudRepository<User, Long> {

}
