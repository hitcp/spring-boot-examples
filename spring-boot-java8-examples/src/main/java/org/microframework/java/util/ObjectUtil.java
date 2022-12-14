package org.microframework.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.microframework.java.entity.User;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-28
 */
public class ObjectUtil extends ObjectUtils {

    /**
     * 将Object（String）类转换为指定实体类对象
     *
     * @param source 源数据json串或者Object对象
     * @param target 目标实体类，如：User.class，Group.class
     * @param <T>
     * @return
     */
//    public static <T> T convertObject(Object source, Class<T> target) {
//        return new ObjectMapper().convertValue(source, target);
//    }

    /**
     * 将Object（String）类转换为指定类型，兼容上方的convertObject()方法
     *
     * @param source          源数据json串或者Object对象
     * @param targetClassType 目标类型
     *                        1.可以转为自定义类型，如：User.class，Group.class
     *                        2.也可以转为jdk类型，如：HashMap.class，ArrayList.class，LinkedList.class
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T convertObject(Object source, Class<T> targetClassType) {
        try {
            return new ObjectMapper().readValue(source.toString(), targetClassType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将数据转换为json字符串
     *
     * @param source 传入源数据，如：user，userList，userMap
     * @return
     */
    public static String convertJson(Object source) {
        try {
            return new ObjectMapper().writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectUtil() {
    }

//    public static void main(String[] args) {
//        // 测试转换json为自定义对象
//        Object jsonObject = "{\"id\":1,\"name\":\"张小明\"}";
//        User user = ObjectUtil.convertObject(jsonObject, User.class);
//        System.out.println(user);
//
//        // 测试转换json为ArrayList
//        Object jsonList = "[{\"id\":1,\"name\":\"张小明\"},{\"id\":2,\"name\":\"张小明2\"},{\"id\":3,\"name\":\"张小明3\"}]";
//        ArrayList<User> arrayList = ObjectUtil.convertObject(jsonList, ArrayList.class);
//        System.out.println(arrayList);
//        // 测试ArrayList转json
//        System.out.println(convertJson(arrayList));
//
//    }

}

