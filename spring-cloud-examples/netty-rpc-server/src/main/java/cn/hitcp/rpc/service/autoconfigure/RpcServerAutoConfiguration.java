package cn.hitcp.rpc.service.autoconfigure;

import cn.hitcp.rpc.service.common.RpcServerProperties;
import cn.hitcp.rpc.service.serializable.KryoSerializable;
import cn.hitcp.rpc.service.serializable.RpcSerializable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
@Configuration
public class RpcServerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RpcSerializable rpcSerializable() {
        return new KryoSerializable();
    }

    @Bean
    public RpcServerProperties rpcClientProperties(Environment environment) {
        BindResult<RpcServerProperties> result = Binder.get(environment).bind("hitcp.rpc.server", RpcServerProperties.class);
        return result.get();
    }
}
