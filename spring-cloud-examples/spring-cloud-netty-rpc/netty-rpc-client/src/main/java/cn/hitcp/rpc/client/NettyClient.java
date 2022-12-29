package cn.hitcp.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Shaoyu Liu
 * @date 2022-12-29
 */
public class NettyClient {
    private static ExecutorService executor
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler clientHandler;

    public Object getProxy(final Class<?> serviceClass, final String protocolHead) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (clientHandler == null) {
                    initClient();
                }
                clientHandler.setParam(protocolHead + args[0]);
                return executor.submit(clientHandler).get();
            }
        });
    }


    private static void initClient() {
        clientHandler = new NettyClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline pipeline = ch.pipeline();
                                    pipeline.addLast(new StringDecoder());
                                    pipeline.addLast(new StringEncoder());
                                    pipeline.addLast(clientHandler);
                                }
                            }
                    );
            bootstrap.connect("127.0.0.1", 7000).sync();
            System.out.println("客户端启动");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
