//package cn.hitcp.rpc.service;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//import java.time.LocalDateTime;
//
///**
// * @author Shaoyu Liu
// * @date 2022-12-29
// */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
////        System.out.println("msg=" + msg);
////        if (msg.toString().startsWith(Protocol.HEADER)) {
////            String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
////            ctx.writeAndFlush(result);
////        }
//
//        while(true) {
//            Thread.sleep(2000L);
//            System.out.println("msg=" + msg);
//            if (msg.toString().startsWith(Protocol.HEADER)) {
//                String result = new HelloServiceImpl().hello(LocalDateTime.now()+msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
//                ctx.writeAndFlush(result);
//            }
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
