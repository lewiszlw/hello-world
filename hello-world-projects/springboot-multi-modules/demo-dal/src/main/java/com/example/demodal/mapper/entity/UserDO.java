package com.example.demodal.mapper.entity;

import lombok.Data;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/14
 * Time:19:39
 */
@Data
public class UserDO {

    private int id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
