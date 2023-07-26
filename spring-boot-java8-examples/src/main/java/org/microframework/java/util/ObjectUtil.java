package org.microframework.java.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Shaoyu Liu
 * @date 2022-09-28
 */
public class ObjectUtil {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper()
                // null不参加序列化
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // 支持java8的DateApi
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
    }

    /**
     * 将Object（String）类转换为指定类型，兼容上方的convertObject()方法
     *
     * @param source          源数据json串或者Object对象
     * @param targetClassType 目标类型
     *                        1.可以转为自定义类型，如：User.class，Group.class
     *                        2.也可以转为jdk类型，如：HashMap.class，ArrayList.class，LinkedList.class
     * @param <T>
     */
    public static <T> T convertObject(Object source, Class<T> targetClassType) {
        try {
            return MAPPER.readValue(source.toString(), targetClassType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将数据转换为json字符串
     *
     * @param source 传入源数据，如：user，userList，userMap
     */
    public static String convertJson(Object source) {
        try {
            return MAPPER.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 私有化
     */
    private ObjectUtil() {
    }


}



