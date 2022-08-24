package org.microframework.basis.order;

import org.springframework.core.Ordered;

/**
 * @author Shaoyu Liu
 * @date 2022-08-24
 */
public class OrderInterface implements Ordered {
    @Override
    public int getOrder() {
        return 1000;
    }
}
