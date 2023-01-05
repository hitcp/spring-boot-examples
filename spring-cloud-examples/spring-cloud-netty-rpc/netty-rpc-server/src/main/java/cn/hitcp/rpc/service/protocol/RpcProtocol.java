package cn.hitcp.rpc.service.protocol;

import cn.hitcp.rpc.service.common.RpcHeader;

import java.io.Serializable;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcProtocol<T> implements Serializable {
    public static final String NAME = "rpc";

    public static final int DEFAULT_PORT = 20880;

    public static final String DEFAULT_HOST = "127.0.0.1";

    /**
     * 消息头
     */
    private RpcHeader header;

    /**
     * 消息体
     */
    private T body;

    public static String getNAME() {
        return NAME;
    }

    public static int getDefaultPort() {
        return DEFAULT_PORT;
    }

    public static String getDefaultHost() {
        return DEFAULT_HOST;
    }

    public RpcHeader getHeader() {
        return header;
    }

    public void setHeader(RpcHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
