package com.springcloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hello(String name) {
        return restTemplate.getForObject("http://eureka-client:8762/demo/hi?name=" + name, String.class);
    }

}
