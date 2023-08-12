//package way4springstomp;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
///**
// * 第一步,配置WebSocket.需要在配置类上使用@EnableWebSocketMessageBroker开启WebSocket支持
// * 并实现WebSocketMessageBrokerConfigurer接口
// * 重写方法来配置WebSocket
// */
//@Configuration
////通过@EnableWebSocketMessageBroker注解开启使用STOMP协议在传输基于代理(message broker)的消息
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    //注册STOMP协议的节点(endpoint),并映射指定的URL
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry){
//        //注册一个STOMP的endpoint,并指定使用SockJS协议
//        registry.addEndpoint("/endpointWisely").setAllowedOrigins("*").withSockJS();
//
//    }
//    //配置消息代理(Message Broker)
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry){
//        //广播式配置一个/topic消息代理
//        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/app");
//    }
//}
//
