package cn.hitcp.rpc.service.serializable;

/**
 * 序列化反序列化
 * 常见序列化案例：https://developer.aliyun.com/article/783611
 *
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public interface RpcSerializable {
    /**
     * 序列化接口
     *
     * @param t
     * @param <T>
     * @return
     */
    <T> byte[] serializable(T t);

    /**
     * 反序列化接口
     *
     * @param bytes
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> tClass);
}
