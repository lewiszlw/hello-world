package com.github.lewiszlw.springretrydemo.controller;

import com.github.lewiszlw.springretrydemo.dao.UserDao;
import com.github.lewiszlw.springretrydemo.model.User;
import com.github.lewiszlw.springretrydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Integer createUser(@RequestBody User user) {
        log.info("createUser body: {}", user);
        long start = System.currentTimeMillis();
        try {
            return userDao.createUser(user);
        } catch (Exception e) {
            log.info("retry logic duration: {} ms", System.currentTimeMillis() - start);
            throw e;
        }
    }
}
