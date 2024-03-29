package cn.hitcp.rpc.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用方式类似 DubboReference
 *
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RpcReference {

    /**
     * 远程调用的服务版本号
     *
     * @return
     */
    public String version() default "1.0.0";



}
