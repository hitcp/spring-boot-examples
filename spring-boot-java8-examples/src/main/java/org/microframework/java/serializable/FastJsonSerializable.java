package org.microframework.java.serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 1.@JSONField
 * 2.@JSONType
 * 3.SerializeFilter
 * 4.SerializerFeature
 */
public class FastJsonSerializable {

    public static void main(String[] args) {
        serializable1();
        serializable2();
        serializable3();
        serializable4();
    }

    static void serializable1() {

    }

    static void serializable2() {

    }

    static void serializable3() {

    }

    static void serializable4() {
        User user = new User("张三", 20);
        JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat);
    }

}
