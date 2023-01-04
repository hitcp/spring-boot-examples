package cn.hitcp.rpc.service.handler;

import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * RPC 服务端逻辑处理
 * 注意：如果定义了多个Handler则使用RpcServerInitializer去添加封装
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> msg) throws Exception {
        long requestId = msg.getMessageHeader().getRequestId();
        ctx.writeAndFlush(msg);
    }
}