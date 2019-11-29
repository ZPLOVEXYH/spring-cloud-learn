package com.springcloud.ribbon.hystrix.service;

import org.springframework.stereotype.Service;

@Service
public class FeignHystrixServiceImpl implements FeignHystrixService {

    @Override
    public String hi(String name) {
        return "error," + name;
    }
}
