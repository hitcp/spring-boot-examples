package org.microframework.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Websocket 方式进行通信
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-09
 */
@Configuration
public class WebSocketConfig {
    /**
     * Request Method: Websocket
     * Request URL: ws://localhost:8080/websocket/echo
     *
     * @param echoHandler
     * @return
     */
    @Bean
    public HandlerMapping webSocketMapping(WebSocketEchoHandler echoHandler) {
        final Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/websocket/echo", echoHandler);
        final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(map);
        return mapping;
    }
//
//    @Bean
//    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
//        return new WebSocketHandlerAdapter();
//    }
}
