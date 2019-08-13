package com.example.demodal.mapper.user;

import com.example.demodal.bean.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/17
 * Time:10:57
 */
@Mapper
@Component(value = "userMapper")
public interface IUserMapper {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<UserDO> queryAllUsers();
}
