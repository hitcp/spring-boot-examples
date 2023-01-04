package cn.hitcp.rpc.service.autoconfigure;

import cn.hitcp.rpc.service.annotation.RpcReference;
import cn.hitcp.rpc.service.common.RpcServerProperties;
import cn.hitcp.rpc.service.protocol.RpcProtocol;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shaoyu Liu
 * @date 2023-01-03
 */
public class RpcReferenceAnnotationBeanPostProcessor implements BeanFactoryPostProcessor, BeanClassLoaderAware {

    private final Map<String, BeanDefinition> rpcReferenceBeanDefinition = new ConcurrentHashMap<>();

    private RpcServerProperties rpcServerProperties;

    private ClassLoader classLoader;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO 容器启动后放入后置事件，自定义rpc逻辑
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
//            RpcReference rpcReference =
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                Class<?> clazz = ClassUtils.resolveClassName(beanClassName, classLoader);
                RpcReference rpcReference = AnnotationUtils.getAnnotation(clazz, RpcReference.class);
                if (rpcReference != null) {
                    // 不为空则构建实例注入
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder.
                            genericBeanDefinition(RpcReferenceBean.class);
                    builder.setInitMethodName("init");
                    builder.addPropertyValue("interfaceClass", clazz.getTypeName());
                    // 使用注册中心的地址，不需要手动注入地址及端口号
//            builder.addPropertyValue("serviceAddress", rpcClientProperties.getServiceAddress());
//            builder.addPropertyValue("servicePort", rpcClientProperties.getServicePort());

                    builder.addPropertyValue("registryAddress", RpcProtocol.DEFAULT_HOST);
                    builder.addPropertyValue("registryType", 1);

                    BeanDefinition builderBeanDefinition = builder.getBeanDefinition();

                    rpcReferenceBeanDefinition.put(clazz.getName(), builderBeanDefinition);
                }
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public RpcReferenceAnnotationBeanPostProcessor(RpcServerProperties rpcServerProperties) {
        this.rpcServerProperties = rpcServerProperties;
    }
}
