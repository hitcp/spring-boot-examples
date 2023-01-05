package cn.hitcp.rpc.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
public class RpcClientProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.拿到调用的方法类全路径，方法名，版本号
        // 2.封装请求协议数据（请求头，请求体）
        // 3.请求，得到结果判断超时、是否成功
        // 4.返回
        return null;
    }
}
