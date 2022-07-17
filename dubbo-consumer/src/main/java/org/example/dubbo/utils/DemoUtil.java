package org.example.dubbo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoUtil {

    private static String appName;

    @Value("${dubbo.application.name}")
    public void setAppName(String name){
        appName = name;
    }

    public static String getAppName(){
        return appName;
    }
}
