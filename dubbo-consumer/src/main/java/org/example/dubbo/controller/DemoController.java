package org.example.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.dubbo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @DubboReference(version = "${demo.service.version}")
    DemoService demoService;

    @GetMapping("/hello1/{name}")
    public String hello1(@PathVariable("name") String name){
        return demoService.sayHello1(name);
    }

    @GetMapping("/hello2/{name}")
    public String hello2(@PathVariable("name") String name){
        return demoService.sayHello2(name);
    }
}
