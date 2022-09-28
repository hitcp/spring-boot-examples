package org.microframework.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.microframework.java.entity.User;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-28
 */
public class ObjectUtil extends ObjectUtils {

    /**
     * 将Object类转换为指定实体类对象
     *
     * @param source 源数据json串或者Object对象
     * @param target 目标实体类，如：User.class，Group.class
     * @param <T>
     * @return
     */
    public static <T> T convertObject(Object source, Class<T> target) {
        return new ObjectMapper().convertValue(source, target);
    }

    /**
     * 将Object类转换为指定类型，兼容上方的convertObject()方法
     *
     * @param source          源数据json串或者Object对象
     * @param targetClassType 目标类型
     *                        1.可以转为自定义类型，如：User.class，Group.class
     *                        2.也可以转为jdk类型，如：HashMap.class，ArrayList.class，LinkedList.class
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T constructType(Object source, Class<T> targetClassType) {
        T t;
        try {
            t = new ObjectMapper().readValue(source.toString(), targetClassType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * 将数据转换为json字符串
     *
     * @param source 传入源数据，如：user，userList，userMap
     * @return
     */
    public static String convertJson(Object source) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private ObjectUtil() {
    }

    public static <T> T convertObject2(Object source, Class<T> target) throws InstantiationException, IllegalAccessException {
        T t = target.newInstance();
        return t;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, JsonProcessingException {
        // 测试转换json为自定义对象
//        Object jsonObject = "{\"id\":1,\"name\":\"张小明\"}";
////        User user1 = ObjectUtil.convertObject(jsonObject, User.class);
//        User user2 = ObjectUtil.constructType(jsonObject, User.class);
//        System.out.println(user2);

        // 测试转换json为ArrayList
        Object jsonList = "[{\"id\":1,\"name\":\"张小明\"},{\"id\":2,\"name\":\"张小明2\"},{\"id\":3,\"name\":\"张小明3\"}]";
        ArrayList<User> arrayList = ObjectUtil.constructType(jsonList, ArrayList.class);
        System.out.println(arrayList);
        // 测试ArrayList转json
        System.out.println(convertJson(arrayList));

    }

}

