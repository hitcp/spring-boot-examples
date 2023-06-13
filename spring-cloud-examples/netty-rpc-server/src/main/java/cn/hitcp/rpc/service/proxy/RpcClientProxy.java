package cn.hitcp.rpc.service.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
public class RpcClientProxy {

    public <T> T createProxy(Class<T> clazz, String version) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcInvocationHandler(null,clazz, version));
    }

}
