package org.microframework.java.design.strategy;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class FormSubmitHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final
    Map<String, FormSubmitHandler<Serializable>> FORM_SUBMIT_HANDLER_MAP = new HashMap<>(8);

    private ApplicationContext appContext;

    /**
     * 根据提交类型获取对应的处理器
     *
     * @param submitType 提交类型
     * @return 提交类型对应的处理器
     */
    public FormSubmitHandler<Serializable> getHandler(String submitType) {
        return FORM_SUBMIT_HANDLER_MAP.get(submitType);
    }

    @Override
    public void afterPropertiesSet() {
        // 将 Spring 容器中所有的 FormSubmitHandler 注册到 FORM_SUBMIT_HANDLER_MAP
        appContext.getBeansOfType(FormSubmitHandler.class)
                .values()
                .forEach(handler -> FORM_SUBMIT_HANDLER_MAP.put(handler.getSubmitType(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}