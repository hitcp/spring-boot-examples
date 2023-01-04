package cn.hitcp.rpc.service.common;

import cn.hitcp.rpc.service.handler.RpcServerInitializer;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class RpcServer {

    public static void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new RpcServerInitializer())
                    .bind(RpcProtocol.DEFAULT_HOST, RpcProtocol.DEFAULT_PORT)
//                    .channel()
//                    .closeFuture()
                    .sync();
            System.out.println("服务端启动");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
