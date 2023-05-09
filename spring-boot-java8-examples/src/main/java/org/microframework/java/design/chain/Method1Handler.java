package org.microframework.java.design.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class Method1Handler extends AbstractHandler {
    @Override
    void handle() {
        System.out.println("execute method1");
    }
}
