package org.microframework.java.function;

import java.math.BigDecimal;

/**
 * @author Shaoyu Liu
 * @date 2023/10/13
 */
public class StrategyInterfaceImpl2 implements StrategyInterface {
    @Override
    public BigDecimal method(BigDecimal num) {
        return BigDecimal.valueOf(100).subtract(num);
    }
}
