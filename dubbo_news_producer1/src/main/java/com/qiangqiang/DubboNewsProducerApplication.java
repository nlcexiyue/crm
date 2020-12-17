package com.qiangqiang;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiangqiang.mapper")
@EnableDubboConfig
public class DubboNewsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboNewsProducerApplication.class, args);
    }

}
