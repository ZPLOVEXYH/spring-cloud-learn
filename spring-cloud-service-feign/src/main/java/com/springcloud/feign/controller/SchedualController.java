package com.springcloud.feign.controller;

import com.springcloud.feign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class SchedualController {

    @Autowired
    SchedualServiceHi serviceHi;

    @GetMapping(value = "/hi")
    public String schedual(@RequestParam(value = "name") String name){
        return serviceHi.schedualService(name);
    }
}
