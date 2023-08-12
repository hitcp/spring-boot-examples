//package way2spring;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.BinaryMessage;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//
//import java.time.LocalDateTime;
//
///**
// * ws消息处理类
// */
//public class MyWebsocketHandler extends AbstractWebSocketHandler {
//
//    private static Logger log = Logger.getLogger(MyWebsocketHandler.class);
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info("建立ws连接");
////        WsSessionManager.add(session.getId(),session);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        log.info("发送文本消息");
//        // 获得客户端传来的消息
//        String payload = message.getPayload();
//        log.info("server 接收到消息 " + payload);
//        session.sendMessage(new TextMessage("server 发送给的消息 " + payload + "，发送时间:" + LocalDateTime.now().toString()));
//    }
//
//    @Override
//    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
//        log.info("发送二进制消息");
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        log.error("异常处理");
////        WsSessionManager.removeAndClose(session.getId());
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info("关闭ws连接");
////        WsSessionManager.removeAndClose(session.getId());
//    }
//}
