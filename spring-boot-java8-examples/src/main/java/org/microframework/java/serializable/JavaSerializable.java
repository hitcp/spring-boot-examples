package org.microframework.java.serializable;

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
        User u = (User) new ObjectInputStream(is).readObject();

        System.out.println("姓名：" + u.getName());
        System.out.println("年龄：" + u.getAge());
    }
}





