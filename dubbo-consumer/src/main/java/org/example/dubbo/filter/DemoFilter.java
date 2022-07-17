package org.example.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.example.dubbo.utils.DemoUtil;

@Activate(group = "consumer")
public class DemoFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appName = DemoUtil.getAppName();
        System.out.println("appName:" + appName);
        RpcContext.getContext().setAttachment("appName", appName);
        return invoker.invoke(invocation);
    }
}
