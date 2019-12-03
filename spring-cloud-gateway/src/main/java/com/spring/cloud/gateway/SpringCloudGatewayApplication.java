package com.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/new")
                        .filters(f -> f.addRequestHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6cCIsImlzcyI6Imh0dHA6Ly93d3cuc2FtcGxlLmNuIiwiaWF0IjoxNTc1MzM5ODM0fQ.gME4q_IlprrOTsGkVBEOQe01jmglirOqHWTx33vBiyn_VA4jchmfz19hFvatH6rgAbN5PMiJL_BydL93MUfoXw"))
                        .uri("http://192.168.43.101:8088/api/CRoles/")
                ).build();
    }

}
