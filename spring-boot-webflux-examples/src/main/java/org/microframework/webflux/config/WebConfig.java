package org.microframework.webflux.config;

import org.microframework.webflux.handler.WebEchoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.server.WebHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Http方式进行通信
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-09
 */
@Configuration
public class WebConfig {
    /**
     * Request Method: GET
     * Request URL: http://localhost:8080/web/echo
     *
     * @param webHandler
     * @return
     */
    @Bean
    public HandlerMapping simpleWebHandlerMapping(final WebEchoHandler webHandler) {
        final Map<String, WebHandler> map = new HashMap<>();
        map.put("/web/echo", webHandler);
        final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(map);
        return mapping;
    }
}
