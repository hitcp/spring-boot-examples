package org.microframework.java.util;

import org.springframework.util.AlternativeJdkIdGenerator;

/**
 * @author Shaoyu Liu
 * @date 2022-09-13
 */
public class IdGenerateUtil {
    private static final AlternativeJdkIdGenerator ALTERNATIVE_JDK_ID_GENERATOR = new AlternativeJdkIdGenerator();

    public static String uuid() {
        return ALTERNATIVE_JDK_ID_GENERATOR.generateId().toString();
    }

    public static String uuidSimple() {
        return ALTERNATIVE_JDK_ID_GENERATOR.generateId().toString().replace("-", "");
    }

    private IdGenerateUtil() {
    }
}
