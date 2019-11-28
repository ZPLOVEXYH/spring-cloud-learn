package com.springcloud.ribbon.controller;

import com.springcloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/ribbon")
    public String hel(@RequestParam(value = "name", defaultValue = "测试") String name){
        return helloService.hello(name);
    }
}
