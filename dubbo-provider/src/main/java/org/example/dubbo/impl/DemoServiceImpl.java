package org.example.dubbo.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.dubbo.service.DemoService;

@DubboService(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {
    public String sayHello1(String name) {
        return "hello 1, " + name;
    }

    public String sayHello2(String name) {
        return "hello 2, " + name;
    }
}
