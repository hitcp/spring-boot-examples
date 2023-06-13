package cn.hitcp.rpc.service.common;

import java.io.Serializable;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcRequest implements Serializable {
    /**
     * 请求全类名
     */
    private String serviceName;
    /**
     * 请求方法名
     */
    private String methodName;
    /**
     * 请求参数
     */
    private Object[] params;
    /**
     * 参数类型
     */
    private Class<?>[] paramsTypes;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<?>[] getParamsTypes() {
        return paramsTypes;
    }

    public void setParamsTypes(Class<?>[] paramsTypes) {
        this.paramsTypes = paramsTypes;
    }
}
