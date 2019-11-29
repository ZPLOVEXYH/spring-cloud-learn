package com.springcloud.hystrix.controller;

import com.springcloud.hystrix.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    HystrixService hystrixService;

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "hystrix") String name) {
        return hystrixService.hi(name);
    }
}
