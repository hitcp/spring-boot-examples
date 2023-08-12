//package way1tomcat;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 访问地址： ws://localhost:8080/websocket/123
// * <p>
// * 2023-08-01 09:43:55.151  INFO 11072 --- [nio-8080-exec-2] way1.TomcatWebsocketService              : 客户端程序123建立连接成功!------>当前在线人数为：1
// * 2023-08-01 09:44:35.970  WARN 11072 --- [nio-8080-exec-1] way1.TomcatWebsocketService              : 客户端程序123已有连接,无需建立连接
// * 2023-08-01 09:46:56.802  INFO 11072 --- [nio-8080-exec-4] way1.TomcatWebsocketService              : 客户端程序123断开连接成功!------>当前在线人数为：0
// *
// * @author Shaoyu Liu
// * @date 2023/7/31
// */
//
//@Component
//@ServerEndpoint("/websocket/{sessionId}")
//public class TomcatWebsocketService {
//    public static final Logger log = LoggerFactory.getLogger(TomcatWebsocketService.class);
//    public static TomcatWebsocketService service = new TomcatWebsocketService();
//    /**
//     * 存放所有在线的客户端 [sessionId, session]
//     */
//    private static final Map<String, Session> clients = new ConcurrentHashMap<>();
//
//    public void sendMessage(String sessionId, String message) {
//        if (!clients.containsKey(sessionId)) {
//            log.error("未找到指定客户端{}的websocket连接!", sessionId);
//            return;
//        }
//
//        this.onMessage(message, clients.get(sessionId));
//        log.info("准备向客户端程序{}发送消息:{}", sessionId, message);
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        try {
//            session.getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            log.error("消息发送失败!", e);
//        }
//    }
//
//    @OnOpen
//    public void onOpen(@PathParam("sessionId") String sessionId, Session session) {
//        if (clients.containsKey(sessionId)) {
//            log.warn("客户端程序{}已有连接,无需建立连接", sessionId);
//            return;
//        }
//        session.setMaxIdleTimeout(0);
//        clients.put(sessionId, session);
//        log.info("客户端程序{}建立连接成功!------>当前在线人数为：{}", sessionId, getOnlineCount());
//    }
//
//    @OnClose
//    public void onClose(@PathParam("sessionId") String sessionId, Session session) {
//        if (!clients.containsKey(sessionId)) {
//            log.warn("客户端程序{}没有连接,无需断开连接", sessionId);
//            return;
//        }
//
//        clients.remove(sessionId);
//        log.info("客户端程序{}断开连接成功!------>当前在线人数为：{}", sessionId, getOnlineCount());
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        log.info("连接{}发生错误!", session.getId());
//        throwable.printStackTrace();
//    }
//
//    public boolean sessionIsOpen(String sessionId) {
//        return clients.get(sessionId).isOpen();
//    }
//
//    public synchronized int getOnlineCount() {
//        return clients.size();
//    }
//
//}
