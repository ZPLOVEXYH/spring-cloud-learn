package com.springcloud.ribbon.hystrix.controller;

import com.springcloud.ribbon.hystrix.service.FeignHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fh")
public class FeignHystrixController {

    @Autowired
    FeignHystrixService feignHystrixService;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "feignhystrix") String name) {
        return feignHystrixService.hi(name);
    }
}
