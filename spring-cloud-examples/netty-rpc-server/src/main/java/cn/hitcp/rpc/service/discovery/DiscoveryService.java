package cn.hitcp.rpc.service.discovery;

import cn.hitcp.rpc.service.common.ServiceInfo;

/**
 * @Classname DiscoveryService
 * @Description
 * @Date 2021/7/23 18:28
 * @Created by wangchangjiu
 */
public interface DiscoveryService {

    /**
     *  发现
     * @param serviceName
     * @return
     * @throws Exception
     */
    ServiceInfo discovery(String serviceName) throws Exception;

}
