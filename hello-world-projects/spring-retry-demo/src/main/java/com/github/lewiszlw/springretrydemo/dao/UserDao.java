package com.github.lewiszlw.springretrydemo.dao;

import com.github.lewiszlw.springretrydemo.model.User;
import com.mysql.cj.exceptions.CJCommunicationsException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@Retryable(include = {CJCommunicationsException.class}, listeners = {"userDaoRetryListener"})
public interface UserDao {

    User queryUserById(@Param("id") int id);

    int createUser(@Param("user") User user);

}
