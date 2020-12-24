package com.qiangqiang;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiangqiang.mapper")
public class DubboSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSpringSecurityApplication.class, args);
    }

}
