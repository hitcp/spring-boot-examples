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
    private RpcHeader messageHeader;

    /**
     * 消息体
     */
    private T messageBody;

    public RpcHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(RpcHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public T getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(T messageBody) {
        this.messageBody = messageBody;
    }
}
