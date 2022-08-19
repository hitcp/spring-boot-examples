package org.microframework.webflux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;


/**
 * Spring Webflux 接口实现方式：1.Controller方式；2.Bean Router方式
 *
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
@Configuration
public class WebfluxRouter {
    @Bean
    public RouterFunction<ServerResponse> fluxRouter(HelloService helloService) {
        return RouterFunctions.route(RequestPredicates.GET("/fluxRouter")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                helloService::hello);
    }
}

//@Service
@Component
class HelloService {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring WebFlux fluxRouter!"));
    }
}

