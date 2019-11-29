package com.springcloud.ribbon.hystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client", fallback = FeignHystrixServiceImpl.class)
public interface FeignHystrixService {

    @GetMapping("/hi")
    String hi(@RequestParam(value = "name") String name);
}
