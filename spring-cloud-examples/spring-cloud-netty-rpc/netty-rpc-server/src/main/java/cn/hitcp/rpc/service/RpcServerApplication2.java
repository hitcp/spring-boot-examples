package cn.hitcp.rpc.service;

import cn.hitcp.rpc.service.common.RpcServerProperties;
import cn.hitcp.rpc.service.common.RpcServer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
@EnableConfigurationProperties(RpcServerProperties.class)
public class RpcServerApplication2 {
    public static void main(String[] args) {
        RpcServer.start();
    }
}
