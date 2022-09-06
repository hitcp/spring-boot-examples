package org.microframework.java.serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

/**
 * 1.@JSONField：用在属性或者get/set方法上
 * 2.@JSONType：序列化指定字段
 * 3.SerializeFilter
 * 4.SerializerFeature
 */
public class FastJsonSerializable {

    public static void main(String[] args) {
//        serializable1();
//        serializable2();
//        serializable3();
        serializable4();
    }

    /**
     * 方式1.@JSONField：用在属性或者get/set方法上
     */
    static void serializable1() {
        User1 user1 = new User1("张三", 20);
        System.out.println("原对象：" + user1);

        // 序列化
        Object serializableUserObject = JSON.toJSON(user1);
        System.out.println("序列化结果：" + serializableUserObject);

        // 反序列化
        User1 deserializableUserObject = JSON.parseObject(serializableUserObject.toString(), User1.class);
        System.out.println("反序列化结果：" + deserializableUserObject);
    }

    /**
     * 方式2.@JSONType：includes指定序列化字段，ignores指定不需要序列化的字段（默认，可不写）
     */
    static void serializable2() {
        User2 user2 = new User2("张三", 20, "张三的描述内容");
        System.out.println("原对象：" + user2);

        // 序列化
        Object serializableUserObject = JSON.toJSON(user2);
        System.out.println("序列化结果：" + serializableUserObject);

        // 反序列化
        User2 deserializableUserObject = JSON.parseObject(serializableUserObject.toString(), User2.class);
        System.out.println("反序列化结果：" + deserializableUserObject);
    }

    /**
     * 方式3.SerializeFilter
     */
    static void serializable3() {
        // TODO
    }

    /**
     * 方式4.SerializerFeature指定序列化内容格式
     *
     * @see SerializerFeature 多种序列化方式
     * QuoteFieldNames：输出key时是否使用双引号,默认为true
     * WriteMapNullValue：是否输出值为null的字段,默认为false
     * WriteNullNumberAsZero：数值字段如果为null,输出为0,而非null
     * WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty：字符类型字段如果为null,输出为”“,而非null
     * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
     * WriteDateUseDateFormat：格式化Date类型为yyyy-MM-dd HH:mm:ss
     *
     * @see Feature#OrderedField 按照目标对象字段顺序序列化
     */
    static void serializable4() {
        Date createDate = new Date();
        User4 user4 = new User4("张三", 20, createDate);
        System.out.println("原对象：" + user4);

        // 序列化
        String serializableUserObject = JSON.toJSONString(user4, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("序列化结果：" + serializableUserObject);

        // 反序列化
        User4 deserializableUserObject = JSON.parseObject(serializableUserObject, User4.class, Feature.OrderedField );
        System.out.println("反序列化结果：" + deserializableUserObject);
    }

}

class User1 {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "NAME")
    private String name;
    @JSONField(name = "AGE", serialize = false, deserialize = false)
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User1() {
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 方式2
 */
@JSONType(includes = {"name", "age"}, ignores = {"desc"})
class User2 {
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User2(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public User2() {
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}

class User4 {
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Date createDate;

    public User4(String name, Integer age, Date createDate) {
        this.name = name;
        this.age = age;
        this.createDate = createDate;
    }

    public User4() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User4{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
