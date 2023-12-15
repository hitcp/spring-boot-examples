package cn.hitcp.rpc.service.codec;

import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.serializable.RpcSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 解码是利用 netty 的 ByteToMessageDecoder 类实现。 实现 decode 方法，ByteToMessageDecoder 继承 ChannelInboundHandlerAdapter。
 * 解码就是将 ByteBuf 中数据解析出请求的数据。
 * 解码要注意 TCP 粘包和拆包问题
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private RpcSerializable rpcSerializable;

    public RpcDecoder(@Autowired @Qualifier("kryoSerializable") RpcSerializable rpcSerializable) {
        this.rpcSerializable = rpcSerializable;
    }

    public RpcDecoder() {
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object deserialize = rpcSerializable.deserialize(in.array(), RpcRequest.class);
        out.add(deserialize);
        ctx.flush();
    }
}
