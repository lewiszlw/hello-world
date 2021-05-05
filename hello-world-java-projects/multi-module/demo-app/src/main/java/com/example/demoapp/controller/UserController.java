package com.example.demoapp.controller;

import com.example.demoapp.Person;
import com.example.demoapp.response.WebResponse;
import com.example.demobiz.user.UserBiz;
import com.example.demodal.bean.UserDO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/14
 * Time:19:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping("/list")
    public WebResponse list() {
        List<UserDO> data = userBiz.queryAllUsers();
        if (null == data) {
            return new WebResponse(0, "查询失败", null);
        } else {
            return new WebResponse(1, "查询成功", data);
        }
    }

    @RequestMapping(value = "/tst", method = RequestMethod.POST)
    public ResponseEntity tst(final HttpServletRequest request, @RequestBody final Person person) {
        System.out.println(person.getName());
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
