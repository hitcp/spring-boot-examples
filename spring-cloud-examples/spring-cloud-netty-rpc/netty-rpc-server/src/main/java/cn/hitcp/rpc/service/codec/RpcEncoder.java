package cn.hitcp.rpc.service.codec;

import cn.hitcp.rpc.service.serializable.Serializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Serializable serializable;

    public RpcEncoder(Serializable serializable) {
        this.serializable = serializable;
    }

    public RpcEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //FIXME 数据序列化编码
        byte[] serializable = this.serializable.serializable(msg);
        out.writeBytes(serializable);
        ctx.flush();
    }
}
