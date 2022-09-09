package org.microframework.webflux.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.microframework.webflux.entity.User;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-09
 */
@Component
public class WebEchoHandler implements WebHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("张三");
        DataBuffer wrap;
        try {
            wrap = bufferFactory.wrap(objectMapper.writeValueAsBytes(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer finalWrap = wrap;
        return response.writeWith(Mono.fromSupplier(() -> finalWrap));
    }
}
