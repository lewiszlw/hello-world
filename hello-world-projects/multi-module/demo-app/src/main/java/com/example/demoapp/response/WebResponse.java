package com.example.demoapp.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei02@meituan.com
 * Date:2018/9/14
 * Time:19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> {
    /**
     * 响应状态：1成功，0失败
     */
    private int status;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private T data;
}
