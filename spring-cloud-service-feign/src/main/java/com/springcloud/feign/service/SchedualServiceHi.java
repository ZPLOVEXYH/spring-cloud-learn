package com.springcloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-client")
public interface SchedualServiceHi {

    @GetMapping("/demo/hi")
    String schedualService(@RequestParam(value = "name") String name);
}
