package com.github.lewiszlw.springretrydemo.service;

import com.github.lewiszlw.springretrydemo.dao.UserDao;
import com.github.lewiszlw.springretrydemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@Retryable(listeners = {"userServiceRetryListener"})
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(final int id) {
        log.info("execution time: {}", new Date());
        throw new RuntimeException("test retry");
    }
}
