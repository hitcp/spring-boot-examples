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

    /**
     * 网络传输，使用netty,代码固定的，值得注意的是 handler 的顺序不能弄错，编码是出站操作（可以放在入站后面），解码和收到响应都是入站操作，解码要在前面。
     */
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
                                    // 编码 是出站操作 将消息编写二进制
                                    pipeline.addLast(new StringDecoder());
                                    // 解码 是入站操作 将二进制解码成消息
                                    pipeline.addLast(new StringEncoder());
                                    // 接收响应 入站操作
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
