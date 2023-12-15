package cn.hitcp.rpc.service.proxy;

import cn.hitcp.rpc.service.discovery.ZookeeperDiscoveryService;

import java.lang.reflect.Proxy;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
public class RpcClientProxy {

    public <T> T createProxy(Class<T> clazz, String version) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcInvocationHandler(null,clazz, version,new ZookeeperDiscoveryService("127.0.0.1:2181")));
    }

}
