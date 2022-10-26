package org.microframework.java.util;

import org.apache.commons.lang3.StringUtils;
import org.microframework.java.execption.MyException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Shaoyu Liu
 * @date 2022-10-26
 */
public class ValidatorUtil {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 校验对象参数并根据validate相关注解一次性返回所有验证结果
     *
     * @param t   实体集
     * @param <T> 实体
     */
    public static <T> void validate(T t) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getPropertyPath().toString() + constraintViolation.getMessage());
        }

        if (!messageList.isEmpty()) {
            String error = StringUtils.join(messageList, ";");
            throw new MyException(error);
        }
    }
}
