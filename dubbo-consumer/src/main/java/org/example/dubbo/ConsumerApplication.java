package org.example.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.dubbo.filter.DemoFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo
@PropertySource("classpath:/dubbo-consumer-config.properties")
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
