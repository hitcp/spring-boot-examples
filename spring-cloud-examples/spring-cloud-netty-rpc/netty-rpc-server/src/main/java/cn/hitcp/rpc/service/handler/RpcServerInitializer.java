package cn.hitcp.rpc.service.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.handler.codec.compression.LzfDecoder;
import io.netty.handler.codec.compression.LzfEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
//                .addLast(new ProtobufEncoder())
//                .addLast(new ProtobufDecoder(
//                        PersonProto.Person.getDefaultInstance()))// FIXME 公用协议包装对象
                .addLast(new JdkZlibEncoder())  // netty 序列化方式 @see io.netty.handler.codec.compression
                .addLast(new JdkZlibDecoder())
                .addLast(new RpcServerHandler());
    }
}
