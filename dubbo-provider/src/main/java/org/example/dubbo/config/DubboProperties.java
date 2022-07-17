package org.example.dubbo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@PropertySource(value = "classpath:/dubbo-provider-config.properties")
@ConfigurationProperties(prefix = "dubbo.blacklist")
public class DubboProperties {
    List<String> apps;
    List<String> interfaces;

    public boolean containsApp(String appName){
        return apps.contains(appName);
    }

    public boolean containsInterface(String interfaceName){
        return interfaces.contains(interfaceName);
    }
}
