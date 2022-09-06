package org.microframework.java.serializable;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * hessian协议与jdk区别
 * 区别一：java序列化无法跨语言
 * 区别二：新旧对象的版本Java通过一个serialVersionUID来关联，需要开发者关注序列化的语义
 * 区别三：java序列化不支持加密
 * 区别四：Java序列化的内容比hessian大
 */
public class HessianSerializable {

    public static void main(String[] args) throws IOException {
        serializable();
    }

    static void serializable() throws IOException {
        User user = new User("张三", 20);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // Hessian的序列化输出
        new HessianOutput(os).writeObject(user);

        byte[] userByte = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);

        // Hessian的反序列化读取对象
        User deserializableUserObject = (User) new HessianInput(is).readObject();
        System.out.println(deserializableUserObject);
    }
}

