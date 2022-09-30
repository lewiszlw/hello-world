package com.example.demodal.mapper;

import com.example.demodal.mapper.entity.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/17
 * Time:10:57
 */
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     */
    List<UserDO> queryAllUsers();
}
