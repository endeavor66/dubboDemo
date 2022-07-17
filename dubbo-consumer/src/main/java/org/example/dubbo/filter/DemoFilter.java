package org.example.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.rpc.*;
import org.springframework.beans.factory.annotation.Autowired;

@Activate(group = "consumer")
public class DemoFilter implements Filter {

    @Autowired
    ApplicationConfig applicationConfig;

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        String appName = applicationConfig.getName();
        String appName = "consumer1";
        System.out.println("appName:" + appName);
        RpcContext.getContext().setAttachment("appName", appName);
        return invoker.invoke(invocation);
    }
}
