package cn.hitcp.rpc.service.protocol;

import cn.hitcp.rpc.service.common.RpcHeader;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义RPC协议，就是通信双方事先商量好规则，服务端知道发送过来的数据将如何解析
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
@Data
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

}
