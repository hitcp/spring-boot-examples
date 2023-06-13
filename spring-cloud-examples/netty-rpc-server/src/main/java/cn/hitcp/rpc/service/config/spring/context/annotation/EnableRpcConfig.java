package cn.hitcp.rpc.service.config.spring.context.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shaoyu Liu
 * @date 2023-01-04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EnableRpcConfigRegister.class)
public @interface EnableRpcConfig {
}
