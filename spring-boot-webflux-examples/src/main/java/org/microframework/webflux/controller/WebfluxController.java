package org.microframework.webflux.controller;

import org.microframework.webflux.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Webflux 接口实现方式：1.Controller方式；2.Bean Router方式
 *
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
@RestController
@RequestMapping("webflux")
public class WebfluxController {

    /**
     * Mono 返回单个对象以及属性使用
     *
     * @return
     */
    @GetMapping("/monoString")
    public Mono<String> monoString() {
        return Mono.fromSupplier(this::getMonoValue);
    }

    @GetMapping("/monoObject")
    public Mono<User> monoObject() {
        return Mono.just(new User("小明"));
    }

    /**
     * Flux 返回对象集合或者数组使用，
     *
     * @return
     */
    @GetMapping(value = "/fluxArray", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // TODO produces类型 为什么非要是这个
    public Flux<String> fluxArray() {
        return Flux.fromArray(new String[]{"张三", "李四", "小明", "小红"}).map(name -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            return "姓名:" + name;
        });
    }

    @GetMapping(value = "/fluxListString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> fluxList() throws InterruptedException {
        return Flux.just(
                method1(),
                method2(),
                method3()
        );
    }

    @GetMapping(value = "/fluxListObject", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<User> fluxListObject() throws InterruptedException {
        return Flux.just( // 一个Object或者list
                method1User(),
                method2User(),
                method3User()
        );
    }


    private String getMonoValue() {
        return "hello";
    }

    private String method1() throws InterruptedException {
        Thread.sleep(1000);
        return "method1Result";
    }

    private String method2() {
        return "method2Result";
    }

    private String method3() {
        return "method3Result";
    }

    private User method1User() throws InterruptedException {
        Thread.sleep(1000);
        return new User("method1Result");
    }

    private User method2User() {
        return new User("method2Result");
    }

    private User method3User() {
        return new User("method3Result");
    }

}


