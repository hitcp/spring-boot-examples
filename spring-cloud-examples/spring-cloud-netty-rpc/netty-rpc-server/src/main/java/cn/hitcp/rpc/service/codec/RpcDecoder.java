package cn.hitcp.rpc.service.codec;

import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.serializable.Serializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Serializable kryoSerializable;

    public RpcDecoder(Serializable serializable) {
        this.kryoSerializable = serializable;
    }

    public RpcDecoder() {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // FIXME 数据反序列化编码
        byte b = in.readByte();
        Object deserialize = kryoSerializable.deserialize(in.array(), RpcRequest.class);
        out.add(deserialize);
        ctx.flush();
    }
}
