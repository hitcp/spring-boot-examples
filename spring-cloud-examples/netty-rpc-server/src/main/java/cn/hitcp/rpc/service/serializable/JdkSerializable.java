package cn.hitcp.rpc.service.serializable;

import java.io.*;

/**
 * @author Shaoyu Liu
 * @date 2023-01-05
 */
public class JdkSerializable implements RpcSerializable {
    @Override
    public <T> byte[] serializable(T t) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(t);
            oos.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("序列化类型IO错误: " + t.getClass(), e);
        }
        return os.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        } catch (IOException ex) {
            throw new IllegalArgumentException("反序列化对象IO错误：", ex);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("反序列化类型没找到错误：", ex);
        }
    }
}
