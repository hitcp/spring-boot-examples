package cn.hitcp.rpc.service.annotation;

import java.lang.annotation.*;

/**
 * 使用方式类似 DubboService
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface RpcService {
    /**
     * Interface class, default value is void.class
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 远程调用的服务版本号
     *
     * @return
     */
    public String version() default "1.0.0";
}
