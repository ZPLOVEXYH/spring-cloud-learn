package com.springcloud.feign.controller;

import com.springcloud.feign.service.SchedualServiceHi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
@Slf4j
public class SchedualController {

    @Autowired
    SchedualServiceHi serviceHi;

    @GetMapping(value = "/hi")
    public String schedual(@RequestParam(value = "name") String name){
        log.info("已进入feign接口，name：{}", name);
        return serviceHi.schedualService(name);
    }
}
