package org.microframework.java.io.serializable;

import org.springframework.util.SerializationUtils;

import java.io.*;

/**
 * 序列化:将对象转换为二进制流的过程称为对象的序列化
 * 反序列化:将二进制流恢复为对象的过程称为对象的反序列化
 */
public class JavaSerializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializable();
    }

    static void serializable() throws IOException, ClassNotFoundException {
        User user = new User("张三", 20);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // jdk序列化
        new ObjectOutputStream(os).writeObject(user);

        byte[] userByte = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);

        // jdk反序列化
        User deserializableUserObject = (User) new ObjectInputStream(is).readObject();
        System.out.println(deserializableUserObject);


        byte[] userByte2 = SerializationUtils.serialize(user);
        User deserialize =  (User)SerializationUtils.deserialize(userByte2);
        System.out.println(deserialize);


    }
}





