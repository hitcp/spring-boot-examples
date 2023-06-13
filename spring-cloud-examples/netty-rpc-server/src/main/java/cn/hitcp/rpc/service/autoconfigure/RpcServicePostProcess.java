package cn.hitcp.rpc.service.autoconfigure;

import cn.hitcp.rpc.service.annotation.RpcService;
import cn.hitcp.rpc.service.common.RpcServerProperties;
import cn.hitcp.rpc.service.handler.ResponseCache;
import cn.hitcp.rpc.service.common.ServiceInfo;
import cn.hitcp.rpc.service.register.RegisterService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Shaoyu Liu
 * @date 2023-01-06
 */
public class RpcServicePostProcess implements BeanPostProcessor {

    private final RegisterService serviceRegister;

    private final RpcServerProperties rpcServerProperties;

    public RpcServicePostProcess(RegisterService serviceRegister, RpcServerProperties rpcServerProperties) {
        this.serviceRegister = serviceRegister;
        this.rpcServerProperties = rpcServerProperties;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
        if (rpcService != null) {
            String serviceName = rpcService.interfaceClass().getName();
            String version = rpcService.version();
            String serviceAndVersion = String.join("-", serviceName, version);

            ResponseCache.put(serviceAndVersion, bean);

            ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setServiceName(serviceAndVersion);
            serviceInfo.setVersion(version);
            serviceInfo.setAppName(rpcServerProperties.getAppName());
            serviceInfo.setHost(rpcServerProperties.getHost());
            serviceInfo.setPort(rpcServerProperties.getPort());

            // TODO 服务注册
            serviceRegister.register(serviceInfo);
        }
        return bean;
    }
}
