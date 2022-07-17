服务鉴权
1.在provider中dubbo-provider-config.properties文件中配置“黑名单”，格式：
dubbo.blacklist[0]=appName:method1,method2,..
dubbo.blacklist[1]=appName:method1,method2,..
注：支持通配符。如：appName:*，表示provider的所有方法都在黑名单中
2.consumer必须在dubbo-consumer-config.properties文件中配置dubbo.application.name。作为鉴权的应用名appName
