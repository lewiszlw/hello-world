package com.example.demobiz.user;

import com.example.demodal.bean.UserDO;
import com.example.demodal.mapper.user.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/14
 * Time:19:34
 */
@Service
public class UserBiz {

    @Autowired
    private IUserMapper userMapper;

    public List<UserDO> queryAllUsers() {
        return userMapper.queryAllUsers();
    }
}
