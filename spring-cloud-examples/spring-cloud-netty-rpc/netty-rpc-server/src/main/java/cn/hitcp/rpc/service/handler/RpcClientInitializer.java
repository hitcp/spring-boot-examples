package cn.hitcp.rpc.service.handler;

import cn.hitcp.rpc.service.codec.RpcDecoder;
import cn.hitcp.rpc.service.codec.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

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
                .addLast(new RpcDecoder())
                .addLast(new RpcEncoder())
                .addLast(new RpcClientHandler());
    }
}
