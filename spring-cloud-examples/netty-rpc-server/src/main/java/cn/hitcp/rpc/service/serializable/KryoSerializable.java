package cn.hitcp.rpc.service.serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public class KryoSerializable implements RpcSerializable {

    @Override
    public <T> byte[] serializable(T t) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Kryo kryo = new Kryo();
        Output output = new Output(os);
        kryo.writeClass(output, t.getClass());

        return os.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Kryo kryo = new Kryo();
        Input input = new Input(bytes);
        return kryo.readObject(input, clazz);
    }
}
