package cn.hitcp.rpc.service.common;

import java.io.Serializable;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class RpcHeader implements Serializable {
    long requestId;
    byte messageType;
    byte status;


    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
