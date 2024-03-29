package cn.hitcp.rpc.service.codec;

import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import cn.hitcp.rpc.service.serializable.RpcSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 编码利用 netty 的 MessageToByteEncoder 类实现。实现 encode 方法，MessageToByteEncoder 继承 ChannelOutboundHandlerAdapter 。
 * 编码就是将请求数据写入到 ByteBuf 中。
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<RpcRequest>> {

    private RpcSerializable rpcSerializable;

    public RpcEncoder(@Autowired @Qualifier("kryoSerializable") RpcSerializable rpcSerializable) {
        this.rpcSerializable = rpcSerializable;
    }

    public RpcEncoder() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> msg, ByteBuf out) throws Exception {
        byte[] serializable = rpcSerializable.serializable(msg);
        out.writeBytes(serializable);
        ctx.flush();
    }
}
