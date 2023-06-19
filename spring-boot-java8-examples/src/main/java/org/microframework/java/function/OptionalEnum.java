package org.microframework.java.function;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 预警配置表 预警字段枚举
 *
 * @author Shaoyu Liu
 * @date 2023/5/11
 */
@AllArgsConstructor
public enum OptionalEnum {
    /**
     * 预警字段枚举
     */
    SHOULD_PAY(1, "描述1"),
    REALLY_PAY(2, "描述2");

    @Getter
    private final Integer code;

    @Getter
    private final String desc;

//    private final Function<Object, BigDecimal> valueGetter;

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

//    public String getValue(Object salary) {
//        return valueGetter.apply(salary);
//    }

}
