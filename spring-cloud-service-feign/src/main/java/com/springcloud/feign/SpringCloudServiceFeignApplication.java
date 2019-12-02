package com.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient // 通过@EnableDiscoveryClient向服务中心注册
@EnableFeignClients // 开启Feign的功能
public class SpringCloudServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceFeignApplication.class, args);
    }

}