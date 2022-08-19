package org.microframework.webflux.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * FIXME java.lang.NoClassDefFoundError: org/springframework/core/metrics/ApplicationStartup
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
@RunWith(SpringRunner.class)
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