package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = "com.qiangqiang.security.distributed.uaa")
public class DistributedSecurityUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedSecurityUaaApplication.class, args);
    }

}
