package cn.hitcp.rpc.service.conts;

/**
 * 请求状态
 *
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
public enum RpcMessageStatusEnum {
    /**
     * 成功
     */
    SUCCESS((byte) 0),
    /**
     * 失败
     */
    FAIL((byte) 1);

    private final byte code;

    RpcMessageStatusEnum(byte code) {
        this.code = code;
    }

    public static boolean isSuccess(byte code) {
        return RpcMessageStatusEnum.SUCCESS.code == code;
    }

    public byte getCode() {
        return code;
    }
}
