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
}
