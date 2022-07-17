package org.example.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.example.dubbo.config.DubboProperties;

import java.util.concurrent.CompletableFuture;

@Activate(group = "provider")
public class AuthFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        DubboProperties dubboProperties = SpringContextHolder.getBean("dubboProperties");
        if(dubboProperties == null){
            System.out.println("get dubboproperties from context failed");
            return invoker.invoke(invocation);
        }
        String appName = invocation.getAttachment("appName");
        String methodName = invocation.getMethodName();
        System.out.println("filter execute, appName:" + appName + ", methodName:" + methodName);
        if(dubboProperties.containsApp(appName) && dubboProperties.containsInterface(methodName)){
            CompletableFuture b = new CompletableFuture();
            AsyncRpcResult asyncRpcResult = new AsyncRpcResult(b,invocation);
            asyncRpcResult.setValue("appName:" + appName + ", methodName:" + methodName + ", 没有访问权限！！");
            return asyncRpcResult;
        }else{
            System.out.println("appName:" + appName + ", methodName:" + methodName + ", 有访问权限");
            return invoker.invoke(invocation);
        }
    }
}
