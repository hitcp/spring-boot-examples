package cn.hitcp.rpc.service.common;

import cn.hitcp.rpc.service.protocol.RpcProtocol;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class RpcServerProperties {
    private Integer port = RpcProtocol.DEFAULT_PORT;
    private String registerUrl = "127.0.0.1:2181";
    private String appName;

//    static {
//        port = RpcProtocol.DEFAULT_PORT;
//        registerUrl = "127.0.0.1:2181";
//    }

    public RpcServerProperties(Integer port, String registerUrl, String appName) {
        this.port = port;
        this.registerUrl = registerUrl;
        this.appName = appName;
    }

    public RpcServerProperties() {
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
