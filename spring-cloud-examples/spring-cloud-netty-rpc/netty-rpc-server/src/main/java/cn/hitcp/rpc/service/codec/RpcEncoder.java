package cn.hitcp.rpc.service.codec;

import cn.hitcp.rpc.service.serializable.RpcSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcEncoder extends MessageToByteEncoder {

    private RpcSerializable rpcSerializable;

    public RpcEncoder(RpcSerializable rpcSerializable) {
        this.rpcSerializable = rpcSerializable;
    }

    public RpcEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //FIXME 数据序列化编码
        byte[] serializable = this.rpcSerializable.serializable(msg);
        out.writeBytes(serializable);
        ctx.flush();
    }
}
