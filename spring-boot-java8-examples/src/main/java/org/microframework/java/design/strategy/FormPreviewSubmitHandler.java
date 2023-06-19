package org.microframework.java.design.strategy;


import java.io.Serializable;

/**
 * 预览表单时的提交
 */
@Component
public class FormPreviewSubmitHandler implements FormSubmitHandler<Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "preview"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("预览模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        return CommonPairResponse.success("预览模式提交数据成功！", null);
    }
}
