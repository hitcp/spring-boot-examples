package org.microframework.java.function;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 预警配置表 预警字段枚举
 *
 * @author Shaoyu Liu
 * @date 2023/5/11
 */
@Getter
@AllArgsConstructor
public enum OptionalEnum {
    /**
     * 预警字段枚举
     */
    SHOULD_PAY(1, "描述1", Impl1.class, (Interface i, BigDecimal num1) -> i.method(num1)),
    REALLY_PAY(2, "描述2", Impl2.class, (Interface i, BigDecimal num1) -> i.method(num1));


    private final Integer code;


    private final String desc;

    //    private final Function<Interfacer, BigDecimal> action;
    private final Class<? extends Interface> i;

    private final BiFunction<Interface, BigDecimal, BigDecimal> biFunctionAction;

//    private final Supplier<BigDecimal> supplierAction;

    public static OptionalEnum findByCode(Integer code) {
        return Arrays.stream(OptionalEnum.values()).filter(s -> s.getCode().equals(code)).findFirst().orElse(null);
//        for (SalaryWarningFieldEnum salaryWarningFieldEnum : SalaryWarningFieldEnum.values()) {
//            if (salaryWarningFieldEnum.getCode().equals(code)) {
//                return salaryWarningFieldEnum;
//            }
//        }
//        return null;
    }

    public static String getDescription(Integer code) {
        return Optional.ofNullable(findByCode(code)).map(OptionalEnum::getDesc).orElse(null);
    }

    public BigDecimal getValue(BigDecimal salary) {
        try {
            return biFunctionAction.apply(i.newInstance(), salary);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
//        return biFunctionAction.apply(salary);
    }


    public interface Interface {
        public BigDecimal method(BigDecimal num);
    }

    public class Impl1 implements Interface {

        @Override
        public BigDecimal method(BigDecimal num) {
            return BigDecimal.valueOf(10).subtract(num);
        }
    }

    public class Impl2 implements Interface {

        @Override
        public BigDecimal method(BigDecimal num) {
            return BigDecimal.valueOf(100).subtract(num);
        }
    }


    public static void main(String[] args) {
        SHOULD_PAY.getValue(BigDecimal.valueOf(1));
    }

}
