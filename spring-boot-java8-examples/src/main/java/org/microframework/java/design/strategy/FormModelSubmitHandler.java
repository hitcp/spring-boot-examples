package org.microframework.java.design.strategy;

/**
 * 模型输入时的提交
 */
@Component
public class FormModelSubmitHandler implements FormSubmitHandler<Long> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "model"; }

    @Override
    public CommonPairResponse<String, Long> handleSubmit(FormSubmitRequest request) {
        logger.info("模型提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 模型创建成功后获得模型的 id
        Long modelId = createModel(request);

        return CommonPairResponse.success("模型提交成功！", modelId);
    }

    private Long createModel(FormSubmitRequest request) {
        // 创建模型的逻辑
        return 123L;
    }
}