package org.microframework.java.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    protected static ApplicationContext applicationContext;
    private static DefaultListableBeanFactory beanFactory;
    private static BeanDefinitionRegistry beanDefinitionRegistry;
    private static BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bFactory) throws BeansException {
        beanFactory = (DefaultListableBeanFactory) bFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry bdRegistry) throws BeansException {
        beanDefinitionRegistry = bdRegistry;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static String buildBean(BeanDefinition definition) {
        String beanName = beanNameGenerator.generateBeanName(definition, beanDefinitionRegistry);
        buildBean(beanName, definition);
        return beanName;
    }

    public static String buildBean(String beanName, BeanDefinition definition) {
        beanDefinitionRegistry.registerBeanDefinition(beanName, definition);
        return beanName;
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        if (applicationContext.containsBean(name)) {
            return applicationContext.getBean(name, tClass);
        }
        return null;
    }

    private SpringContextUtil() {
    }
}

