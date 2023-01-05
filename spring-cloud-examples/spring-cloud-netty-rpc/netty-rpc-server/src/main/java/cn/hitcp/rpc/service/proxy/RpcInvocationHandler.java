package cn.hitcp.rpc.service.proxy;

import cn.hitcp.rpc.service.codec.RequestMetadata;
import cn.hitcp.rpc.service.common.RpcClientProperties;
import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.common.RpcResponse;
import cn.hitcp.rpc.service.handler.ResponseCache;
import cn.hitcp.rpc.service.handler.RpcServerInitializer;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
public class RpcInvocationHandler implements InvocationHandler {

    public static final Logger log = LoggerFactory.getLogger(RpcInvocationHandler.class);

    private RpcClientProperties rpcClientProperties;
    private Class<?> calzz;
    private String version;


    public RpcInvocationHandler(RpcClientProperties rpcClientProperties, Class<?> calzz, String version) {
        this.rpcClientProperties = rpcClientProperties;
        this.calzz = calzz;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.拿到调用的方法类全路径，方法名，版本号
        // 2.封装请求协议数据（请求头，请求体）
        // 3.请求，得到结果判断超时、是否成功
        // 4.返回
        // TODO
        return null;
    }


    public RpcProtocol<RpcResponse> sendRequest(RequestMetadata metadata) throws Exception {
        RpcProtocol<RpcRequest> protocol = metadata.getProtocol();
        RpcFuture<RpcProtocol<RpcResponse>> future = new RpcFuture<>();
        ResponseCache.put(protocol.getHeader().getRequestId(), future);

        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
        RpcServerInitializer handler = new RpcServerInitializer();
        // TCP 连接
        ChannelFuture channelFuture = bootstrap.connect(metadata.getAddress(), metadata.getPort()).sync();
        channelFuture.addListener((ChannelFutureListener) arg0 -> {
            if (channelFuture.isSuccess()) {
                log.info("connect rpc server {} on port {} success.", metadata.getAddress(), metadata.getPort());
            } else {
                log.error("connect rpc server {} on port {} failed.", metadata.getAddress(), metadata.getPort());
                channelFuture.cause().printStackTrace();
                eventLoopGroup.shutdownGracefully();
            }
        });
        // 写入数据
        channelFuture.channel().writeAndFlush(protocol);
        return metadata.getTimeout() != null ? future.get(metadata.getTimeout(), TimeUnit.MILLISECONDS) : future.get();
    }
}
