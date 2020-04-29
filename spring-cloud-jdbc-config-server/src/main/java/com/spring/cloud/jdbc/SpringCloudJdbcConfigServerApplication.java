package com.spring.cloud.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudJdbcConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudJdbcConfigServerApplication.class, args);
    }

}
