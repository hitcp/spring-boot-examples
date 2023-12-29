package org.microframework.java.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.Assert;

import java.util.Objects;

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
     * ObjectNode 转换为指定的 java对象集合
     *
     * @param objectNode
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T convertList(ObjectNode objectNode, String key, TypeReference<T> toValueTypeRef) {
        Assert.notNull(key, "key cannot be null");
        ArrayNode arrayNode = objectNode.withArray(key);
        if (Objects.isNull(arrayNode)) {
            return null;
        }
        // 不传入具体类型 list默认返回中的List<LinkedHashMap>类型，所以要把toValueTypeRef参数传进来
        return MAPPER.convertValue(arrayNode, toValueTypeRef);
    }

    /**
     * JsonNode 转换成 java 对象
     *
     * @param treeNode
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T treeToValue(JsonNode treeNode, Class<T> valueType) {
        try {
            return MAPPER.treeToValue(treeNode, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * java 对象转换成 JsonNode
     *
     * @param object
     * @return
     */
    public static JsonNode treeToValue(Object object) {
        return MAPPER.valueToTree(object);
    }

    /**
     * ObjectNode 转换成 JsonNode
     *
     * @param personNode
     * @return
     */
    public static JsonNode readTree(ObjectNode personNode) {
        try {
            return MAPPER.readTree(personNode.toString());
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



