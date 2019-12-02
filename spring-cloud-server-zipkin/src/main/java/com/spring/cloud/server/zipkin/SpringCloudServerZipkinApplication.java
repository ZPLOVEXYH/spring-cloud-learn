package com.spring.cloud.server.zipkin;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class SpringCloudServerZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServerZipkinApplication.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome() {
        log.info("calling trace server-zipkin");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @GetMapping("/info")
    public String info() {
        log.info("calling trace server-zipkin");
        return "i`m server-zipkin";
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
