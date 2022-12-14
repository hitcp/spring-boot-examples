package org.microframework.webflux.router;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


/**
 * FIXME java.lang.NoClassDefFoundError: org/springframework/core/metrics/ApplicationStartup
 *
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
@WebFluxTest
public class WebfluxRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void textFluxRouter() {
        webTestClient.get().exchange().expectStatus().isOk();
    }
}