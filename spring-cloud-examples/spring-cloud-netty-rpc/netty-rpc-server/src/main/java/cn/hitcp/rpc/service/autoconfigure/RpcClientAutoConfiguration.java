package cn.hitcp.rpc.service.autoconfigure;

import cn.hitcp.rpc.service.common.RpcClientProperties;
import cn.hitcp.rpc.service.serializable.KryoSerializable;
import cn.hitcp.rpc.service.serializable.RpcSerializable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
@EnableConfigurationProperties(RpcClientProperties.class)
@Configuration
public class RpcClientAutoConfiguration implements EnvironmentAware {

    private RpcClientProperties properties;

    public RpcClientAutoConfiguration(RpcClientProperties properties) {
        this.properties = properties;
    }

    /**
     * TODO 删除
     *
     * @param environment
     */
    @Deprecated
    @Override
    public void setEnvironment(Environment environment) {
        // TODO 解析配置
        String host = environment.getProperty("cn.hitcp.rpc.server.host");
        String port = environment.getProperty("cn.hitcp.rpc.server.port");

    }

    @Bean
    @ConditionalOnMissingBean
    public RpcSerializable rpcSerializable() {
        return new KryoSerializable();
    }


}
