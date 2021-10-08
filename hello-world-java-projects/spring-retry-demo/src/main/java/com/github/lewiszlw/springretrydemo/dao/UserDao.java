package com.github.lewiszlw.springretrydemo.dao;

import com.github.lewiszlw.springretrydemo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    User queryUserById(@Param("id") int id);

    @Retryable(maxAttempts = 100)
    int createUser(@Param("user") User user);

}
