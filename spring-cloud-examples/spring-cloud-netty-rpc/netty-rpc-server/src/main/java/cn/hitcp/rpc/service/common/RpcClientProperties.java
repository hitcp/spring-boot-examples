package cn.hitcp.rpc.service.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
@ConfigurationProperties(prefix = "cn.hitcp.server")
public class RpcClientProperties {
    private String host;
    private Integer port;
    private Integer timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
