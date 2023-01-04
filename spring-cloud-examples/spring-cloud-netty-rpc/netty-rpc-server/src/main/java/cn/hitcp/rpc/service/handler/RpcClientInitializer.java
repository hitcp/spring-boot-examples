package cn.hitcp.rpc.service.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 自定义逻辑、序列类初始化封装类
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(new RpcClientHandler());
    }
}
