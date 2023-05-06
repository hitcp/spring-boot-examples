package cn.hitcp.rpc.service.common;

import java.io.Serializable;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcResponse implements Serializable {
    private String message;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
