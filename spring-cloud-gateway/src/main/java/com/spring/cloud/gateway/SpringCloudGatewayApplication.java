package com.spring.cloud.gateway;

import com.spring.cloud.gateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    //    @Autowired
//    TokenFilter filter;
//
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
