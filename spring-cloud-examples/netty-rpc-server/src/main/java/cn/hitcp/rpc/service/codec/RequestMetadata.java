package cn.hitcp.rpc.service.codec;



import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.protocol.RpcProtocol;

import java.io.Serializable;

/**
 * @Classname RequestMetadata
 * @Description 请求元数据
 * @Date 2021/7/30 9:47
 * @Created by wangchangjiu
 */

public class RequestMetadata implements Serializable {

    /**
     *  协议
     */
    private RpcProtocol<RpcRequest> protocol;

    /**
     *  地址
     */
    private String address;

    /**
     *  端口
     */
    private Integer port;

    /**
     *  服务调用超时
     */
    private Integer timeout;

    public RpcProtocol<RpcRequest> getProtocol() {
        return protocol;
    }

    public void setProtocol(RpcProtocol<RpcRequest> protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
