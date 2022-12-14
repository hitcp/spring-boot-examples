package org.microframework.basis.import2;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Shaoyu Liu
 * @date 2022-08-23
 */
public class ImportByRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition root = new RootBeanDefinition(ImportByClass.class);
        // 指定beanName，可以自定义beanName
        registry.registerBeanDefinition("importByClass", root);
    }

    static {
        System.out.println("ImportByRegistrar init...");
    }

    public void foo() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
