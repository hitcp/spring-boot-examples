package org.microframework.java.design.chain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;

@SpringBootTest
public class InvokeTest {

    @Autowired
    private List<AbstractHandler> chains;

    @Test
    void testOrderHandler() {
        // 执行顺序，根据@Order排序
        chains.sort(AnnotationAwareOrderComparator.INSTANCE);
        for (AbstractHandler handler : chains) {
            handler.handle();
        }
    }

}

