package cn.hitcp.rpc.service.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.handler.codec.compression.JdkZlibEncoder;

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
                // netty内置编码解码器：https://developer.aliyun.com/article/906789
                .addLast(new JdkZlibEncoder())
                .addLast(new JdkZlibDecoder())
                .addLast(new RpcClientHandler());
    }
}
