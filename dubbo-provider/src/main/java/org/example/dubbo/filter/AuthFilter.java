package org.example.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.example.dubbo.config.DubboProperties;
import org.example.dubbo.utils.AuthUtil;
import org.example.dubbo.utils.SprintContextUtil;

import java.util.concurrent.CompletableFuture;

@Activate(group = "provider")
public class AuthFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appName = invocation.getAttachment("appName");
        String methodName = invocation.getMethodName();
        System.out.println("filter execute, appName:" + appName + ", methodName:" + methodName);
        if(AuthUtil.check(appName, methodName)){
            System.out.println("appName:" + appName + ", methodName:" + methodName + ", 有访问权限");
            return invoker.invoke(invocation);
        }else{
            CompletableFuture b = new CompletableFuture();
            AsyncRpcResult asyncRpcResult = new AsyncRpcResult(b,invocation);
            asyncRpcResult.setValue("appName:" + appName + ", methodName:" + methodName + ", 没有访问权限！！");
            return asyncRpcResult;
        }
    }
}
