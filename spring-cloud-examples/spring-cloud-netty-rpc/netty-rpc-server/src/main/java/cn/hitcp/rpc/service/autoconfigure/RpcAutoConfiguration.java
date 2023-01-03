package cn.hitcp.rpc.service.autoconfigure;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * FIXME dubbo源码只有一个AutoConfiguration，网上查的有两个
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcAutoConfiguration implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        // TODO 解析配置
    }
}
