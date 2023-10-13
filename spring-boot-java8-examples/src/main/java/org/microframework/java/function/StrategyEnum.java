package org.microframework.java.function;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * 根据不同枚举走不同实现
 *
 * @author Shaoyu Liu
 * @date 2023/5/11
 */
@Getter
@AllArgsConstructor
public enum StrategyEnum {
    /**
     * 枚举值
     */
    ENUM_VALUE_1(1, "描述1", StrategyInterfaceImpl1.class, StrategyInterface::method),
    ENUM_VALUE_2(2, "描述2", StrategyInterfaceImpl2.class, StrategyInterface::method);

    private final Integer code;

    private final String desc;

    private final Class<? extends StrategyInterface> impl;

    private final BiFunction<StrategyInterface, BigDecimal, BigDecimal> biFunctionAction;

    public BigDecimal calculate(BigDecimal salary) {
        try {
            return biFunctionAction.apply(impl.newInstance(), salary);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }


    public static void main(String[] args) {
        System.out.println(ENUM_VALUE_1.calculate(BigDecimal.valueOf(1)));
    }

}
