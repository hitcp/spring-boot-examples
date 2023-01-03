package cn.hitcp.rpc.service.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcReferenceAnnotationBeanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO 容器启动后放入后置事件，自定义rpc逻辑
    }
}
