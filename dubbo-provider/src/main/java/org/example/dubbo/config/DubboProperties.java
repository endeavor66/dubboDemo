package org.example.dubbo.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Data
@Component
@PropertySource(value = "classpath:/dubbo-provider-config.properties")
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
    List<String> blacklist;
}
