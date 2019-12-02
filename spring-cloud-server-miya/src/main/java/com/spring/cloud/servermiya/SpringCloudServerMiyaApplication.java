package com.spring.cloud.servermiya;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class SpringCloudServerMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServerMiyaApplication.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String home() {
        log.info("hi is being called");
        return "hi i'm miya!";
    }

    @RequestMapping("/miya")
    public String miya() {
        return restTemplate.getForObject("http://localhost:8988/info", String.class);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
