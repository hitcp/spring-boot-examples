package cn.hitcp.rpc.service.handler;

import cn.hitcp.rpc.service.common.RpcHeader;
import cn.hitcp.rpc.service.common.RpcRequest;
import cn.hitcp.rpc.service.common.RpcResponse;
import cn.hitcp.rpc.service.conts.RpcMessageStatusEnum;
import cn.hitcp.rpc.service.conts.RpcMessageTypeEnum;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * RPC 客户端逻辑处理
 * 注意：如果定义了多个Handler则使用RpcClientInitializer去添加封装
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> msg) throws Exception {

        RpcProtocol<RpcResponse> resProtocol = new RpcProtocol<>();
        RpcResponse response = new RpcResponse();
        RpcHeader header = msg.getHeader();
        // 设置头部消息类型为响应
        header.setMessageType(RpcMessageTypeEnum.RESPONSE.getType());
        try {
            RpcRequest rpcRequest = msg.getBody();
            Object bean = ResponseCache.get(rpcRequest.getClassName());
            if (bean == null) {
                throw new RuntimeException(String.format("service not exist: %s !", rpcRequest.getClassName()));
            }
            // 反射调用
            Method method = bean.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object result = method.invoke(bean, rpcRequest.getParams());
            response.setData(result);
            header.setStatus(RpcMessageStatusEnum.SUCCESS.getCode());
            resProtocol.setHeader(header);
            resProtocol.setBody(response);
        } catch (Throwable throwable) {
            header.setStatus(RpcMessageStatusEnum.FAIL.getCode());
            response.setMessage(throwable.toString());
            System.out.println("process request :" + header.getRequestId() + " ,error :" + throwable);
        }
        // 把数据写回去
        ctx.writeAndFlush(resProtocol);
    }
}
