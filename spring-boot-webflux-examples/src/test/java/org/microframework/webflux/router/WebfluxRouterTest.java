package org.microframework.webflux.router;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

/**
 * FIXME java.lang.NoClassDefFoundError: org/springframework/core/metrics/ApplicationStartup
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class WebfluxRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void textFluxRouter() {
        webTestClient.get().exchange().expectStatus().isOk();
    }
}