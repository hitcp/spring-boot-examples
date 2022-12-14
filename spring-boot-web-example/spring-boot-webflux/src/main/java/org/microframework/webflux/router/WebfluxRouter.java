package org.microframework.webflux.router;

import org.microframework.webflux.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


/**
 * Spring Webflux 接口实现方式：1.Controller方式；2.Bean Router方式
 *
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
@Configuration
public class WebfluxRouter {
    @Bean
    public RouterFunction<ServerResponse> fluxRouterService(UserService userService) {
        return RouterFunctions
                .route(RequestPredicates.GET("/fluxRouter/user/hello").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::hello)
                .andRoute(RequestPredicates.POST("/fluxRouter/user/insert").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::insert)
                .andRoute(RequestPredicates.POST("/fluxRouter/user/delete").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::delete)
                .andRoute(RequestPredicates.POST("/fluxRouter/user/update").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::update)
                .andRoute(RequestPredicates.GET("/fluxRouter/user/select").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::select)
                .andRoute(RequestPredicates.GET("/fluxRouter/user/list").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userService::list);
    }

//    @Bean
//    public RouterFunction<ServerResponse> fluxRouterHandler(HelloHandler helloHandler) {
//        return RouterFunctions
//                .route(RequestPredicates.GET("/fluxRouter").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), helloService::hello);
//    }
}



