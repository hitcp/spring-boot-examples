package org.microframework.webflux.service;

import org.microframework.webflux.dao.UserDao;
import org.microframework.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TODO
 *  1.url?后拼接
 *  2.url域参数：ServerRequest.pathVariable("id")
 *  3.requestBody参数
 *
 * @author Shaoyu Liu---website:<a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-09
 */

@Service
//@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    //    @Override
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new User(1, "张三")));
//                .body(BodyInserters.fromValue("Hello, Spring WebFlux fluxRouter!"));
    }

    public Mono<ServerResponse> insert(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                (Object) serverRequest.bodyToMono(User.class).flatMap(user -> Mono.just(userDao.insert(user))),
                User.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                (Object) serverRequest.bodyToMono(Integer.class).flatMap(id -> Mono.just(userDao.delete(id))),
                User.class);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                (Object) serverRequest.bodyToMono(User.class).flatMap(user -> Mono.just(userDao.update(user))),
                User.class);
    }

    public Mono<ServerResponse> select(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                serverRequest.bodyToMono(Integer.class).flatMap(id -> Mono.just(userDao.select(id))),
                User.class);

        //
//        Mono<Integer> integerMono = serverRequest.bodyToMono(Integer.class);
//        return Mono.justOrEmpty(userDao.select(Long.valueOf(serverRequest.pathVariable("id"))))
//                .flatMap(user -> ServerResponse.ok().body(Mono.just(user), UserModel.class))
//                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> list(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Flux.fromStream(userDao.list().stream()), User.class);
    }

}