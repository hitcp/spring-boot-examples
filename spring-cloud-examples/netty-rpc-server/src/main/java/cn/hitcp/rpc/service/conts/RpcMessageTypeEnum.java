package cn.hitcp.rpc.service.conts;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public enum RpcMessageTypeEnum {
    /**
     * 请求
     */
    REQUEST((byte) 1),
    /**
     * 返回
     */
    RESPONSE((byte) 2);

    private byte type;

    RpcMessageTypeEnum(byte type) {
        this.type = type;
    }

    public static RpcMessageTypeEnum findByType(byte type) {
        for (RpcMessageTypeEnum msgType : RpcMessageTypeEnum.values()) {
            if (msgType.getType() == type) {
                return msgType;
            }
        }
        return null;
    }

    public byte getType() {
        return type;
    }
}
