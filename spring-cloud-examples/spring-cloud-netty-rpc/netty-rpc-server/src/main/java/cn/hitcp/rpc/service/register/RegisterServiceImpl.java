package cn.hitcp.rpc.service.register;

import cn.hitcp.rpc.service.common.ServiceInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

/**
 * @author Shaoyu Liu
 * @date 2023-01-06
 */
public class RegisterServiceImpl implements RegisterService {

    private ServiceDiscovery<ServiceInfo> serviceDiscovery;

    public RegisterServiceImpl(String registryAddr) {
        try {
            CuratorFramework client = CuratorFrameworkFactory.newClient(registryAddr, new ExponentialBackoffRetry(1000, 3));
            client.start();
            JsonInstanceSerializer<ServiceInfo> serializer = new JsonInstanceSerializer<>(ServiceInfo.class);
            this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
                    .client(client)
                    .serializer(serializer)
                    .basePath("rpc")
                    .build();
            this.serviceDiscovery.start();
        } catch (Exception e) {
            System.out.println("ServiceRegisterImpl start error" + e);
        }
    }

    @Override
    public void register(ServiceInfo serviceInfo) {
        try {
            ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance
                    .<ServiceInfo>builder()
                    .address(serviceInfo.getServiceName())
                    .port(1281)
                    .name("127.0.0.1")
                    .build();
            serviceDiscovery.registerService(serviceInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unregister(ServiceInfo serviceInfo) {
        try {
            ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance
                    .<ServiceInfo>builder()
                    .address(serviceInfo.getServiceName())
                    .port(1281)
                    .name("127.0.0.1")
                    .build();
            serviceDiscovery.unregisterService(serviceInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
