package org.example.dubbo.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.example.dubbo.config.DubboProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AuthUtil {

    private static DubboProperties properties;
    private static Map<String, Set<String>> blackMap;

    @Autowired
    public void setProperties(DubboProperties properties){
        this.properties = properties;
    }

    private static void init(){
        blackMap = new HashMap<>();
        List<String> blacklist = properties.getBlacklist();
        if(CollectionUtils.isEmpty(blacklist)){
            return;
        }
        for (String s : blacklist) {
            String[] info = s.split(":");
            Assert.isTrue(info.length == 2, "黑名单格式错误");
            //解析应用名
            String appName = info[0].trim();
            //解析接口
            Iterable<String> iterable = Splitter
                    .on(',')
                    .trimResults()
                    .omitEmptyStrings()
                    .split(info[1]);
            Set<String> methods = Sets.newHashSet(iterable);
            //存入blackMap
            blackMap.put(appName, methods);
        }
    }

    /**
     *
     * @param appName
     * @param methodName
     * @return true(鉴权通过) | false(鉴权不通过)
     */
    public static boolean check(String appName, String methodName){
        if(StringUtils.isEmpty(appName) || StringUtils.isEmpty(methodName)){
            System.out.println("参数校验失败");
            return false;
        }

        if(null == blackMap){
            init();
        }

        if(blackMap.containsKey(appName)){
            Set<String> set = blackMap.get(appName);
            if(set.contains("*") || set.contains(methodName)){
                return false;
            }
        }

        return true;
    }
}
