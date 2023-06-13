package cn.hitcp.rpc.service.register;

import cn.hitcp.rpc.service.common.ServiceInfo;

/**
 * @author Shaoyu Liu
 * @date 2023-01-06
 */
public interface RegisterService {
    /**
     * 注册服务
     *
     * @param serviceInfo
     */
    void register(ServiceInfo serviceInfo);

    /**
     * 注销服务
     *
     * @param serviceInfo
     */
    void unregister(ServiceInfo serviceInfo);
}
