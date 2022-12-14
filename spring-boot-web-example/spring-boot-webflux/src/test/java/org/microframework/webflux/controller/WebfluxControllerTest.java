package org.microframework.webflux.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * FIXME java.lang.NoClassDefFoundError: org/springframework/core/metrics/ApplicationStartup
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
//@RunWith(SpringRunner.class) spring-boot-starter-test 2.5.5 版本只需要在类上加上@SpringBootTest即可，不需要再加@RunWith()注解了
@SpringBootTest
@WebFluxTest(controllers = WebfluxController.class)
public class WebfluxControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testMonoString() {
        webTestClient.get().uri("/webflux/monoString").exchange().expectStatus().isOk();
    }

    @Test
    public void testMonoObject() {
        webTestClient.get().uri("/webflux/monoObject").exchange().expectStatus().isOk();
    }

    @Test
    public void testFluxArray() {
        webTestClient.get().uri("/webflux/fluxArray").exchange().expectStatus().isOk();
    }

    @Test
    public void testFluxList() {
        webTestClient.get().uri("/webflux/fluxList").exchange().expectStatus().isOk();
    }

    @Test
    public void testFluxListObject() {
        webTestClient.get().uri("/webflux/fluxListObject").exchange().expectStatus().isOk();
    }
}