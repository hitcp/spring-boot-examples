//package way2spring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
///**
// * ws://localhost:8080/webSocketServer
// * http://localhost:8080/sockjs/webSocketServer
// * ws://localhost:8080/myWebsocketHandler
// * http://localhost:8080/sockjs/myWebsocketHandler
// * ws://localhost:8080/myWS?session_id=123
// */
//@Configuration
//@EnableWebSocket
//public class SpringWebSocketConfig implements WebSocketConfigurer {
//
////    @Autowired
////    private WebSocketHandler webSocketHandler;
////
////    @Override
////    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        registry.addHandler(webSocketHandler, "/websocket").setAllowedOrigins("*");
////    }
//
////    @Override
////    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        // 第一个addHandler是对正常连接的配置
//////        registry.addHandler(chatMessageHandler(), "/websocket").addInterceptors(new ChatHandshakeInterceptor());
////        registry.addHandler(chatMessageHandler(), "/websocket");
////        // 第二个是如果浏览器不支持websocket，使用socket js模拟websocket的连接。
////        registry.addHandler(chatMessageHandler(), "/sockjs/websocket").withSockJS();
//////        registry.addHandler(webSocketHandler, "/websocket").setAllowedOrigins("*");
////    }
//
////    @Bean
////    public TextWebSocketHandler chatMessageHandler() {
////        return new ChatMessageHandler();
////    }
//
//    @Autowired
//    private MyHttpHandler httpAuthHandler;
//    @Autowired
//    private MyInterceptor myInterceptor;
//
//    @Bean
//    public TextWebSocketHandler chatMessageHandler() {
//        return new MyHttpHandler();
//    }
//
//    @Bean
//    public MyWebsocketHandler myWebsocketHandler() {
//        return new MyWebsocketHandler();
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // 第一个addHandler是对正常连接的配置
//        registry.addHandler(chatMessageHandler(), "/webSocketServer").setAllowedOrigins("*").addInterceptors(new MyInterceptor());
//        // 第二个是如果浏览器不支持websocket，使用socket js模拟websocket的连接。
//        registry.addHandler(chatMessageHandler(), "/sockjs/webSocketServer").setAllowedOrigins("*").addInterceptors(new MyInterceptor()).withSockJS();
//
//
//        // 第一个addHandler是对正常连接的配置
//        registry.addHandler(myWebsocketHandler(), "/myWebsocketHandler").setAllowedOrigins("*").addInterceptors(new MyInterceptor());
//        // 第二个是如果浏览器不支持websocket，使用socket js模拟websocket的连接。
//        registry.addHandler(myWebsocketHandler(), "/sockjs/myWebsocketHandler").setAllowedOrigins("*").addInterceptors(new MyInterceptor()).withSockJS();
//
//        registry.addHandler(httpAuthHandler, "myWS").addInterceptors(myInterceptor).setAllowedOrigins("*");
//    }
//
//
//}
//
