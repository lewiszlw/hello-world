package com.example.demoapp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demodal.mapper")  // 扫描mapper接口
public class MapperConfiguration {
}
