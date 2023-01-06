package cn.hitcp.rpc.service.proxy;

import cn.hitcp.rpc.service.codec.RequestMetadata;
import cn.hitcp.rpc.service.common.*;
import cn.hitcp.rpc.service.conts.ProtocolConstants;
import cn.hitcp.rpc.service.conts.RpcMessageStatusEnum;
import cn.hitcp.rpc.service.conts.RpcMessageTypeEnum;
import cn.hitcp.rpc.service.discovery.DiscoveryService;
import cn.hitcp.rpc.service.exception.RpcException;
import cn.hitcp.rpc.service.handler.RequestCache;
import cn.hitcp.rpc.service.handler.RpcServerInitializer;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
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

    private final DiscoveryService discoveryService;
    private final RpcClientProperties rpcClientProperties;
    private final Class<?> calzz;
    private final String version;


    public RpcInvocationHandler(RpcClientProperties rpcClientProperties, Class<?> calzz, String version, DiscoveryService discoveryService) {
        this.rpcClientProperties = rpcClientProperties;
        this.calzz = calzz;
        this.version = version;
        this.discoveryService = discoveryService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.拿到调用的方法类全路径，方法名，版本号
        // 2.封装请求协议数据（请求头，请求体）
        // 3.请求，得到结果判断超时、是否成功
        // 4.返回
        // TODO
        String serviceAndVersion = String.join("-", this.calzz.getName(), this.version);
        ServiceInfo discovery = discoveryService.discovery(serviceAndVersion);
        if (discovery == null) {
            throw new RpcException("服务不存在" + serviceAndVersion);
        }

        RpcHeader header = new RpcHeader();
        header.setMagic(ProtocolConstants.MAGIC);
        header.setVersion(ProtocolConstants.VERSION);
        header.setRequestId(System.currentTimeMillis());
        header.setMessageType(RpcMessageTypeEnum.REQUEST.getType());

        RpcRequest body = new RpcRequest();
        body.setServiceName(serviceAndVersion);
        body.setMethodName(method.getName());
        body.setParamsTypes(method.getParameterTypes());
        body.setParams(args);

        RpcProtocol<RpcRequest> protocolData = new RpcProtocol<>();
        protocolData.setHeader(header);
        protocolData.setBody(body);

        RequestMetadata request = new RequestMetadata();
        request.setProtocol(protocolData);
        request.setAddress(rpcClientProperties.getHost());
        request.setPort(rpcClientProperties.getPort());
        request.setTimeout(rpcClientProperties.getTimeout());

        RpcProtocol<RpcResponse> result = sendRequest(request);

        if (result == null) {
            throw new RpcException("rpc调用结果失败， 请求超时 timeout:" + rpcClientProperties.getTimeout());
        } else if (RpcMessageStatusEnum.SUCCESS.getCode() != result.getHeader().getStatus()) {
            throw new RpcException("rpc调用结果失败， message：{}" + result.getBody().getMessage());
        }
        return result.getBody().getData();
    }


    public RpcProtocol<RpcResponse> sendRequest(RequestMetadata metadata) throws Exception {
        RpcProtocol<RpcRequest> protocol = metadata.getProtocol();
        RpcFuture<RpcProtocol<RpcResponse>> future = new RpcFuture<>();
        RequestCache.put(protocol.getHeader().getRequestId(), future);

        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new RpcServerInitializer());

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
