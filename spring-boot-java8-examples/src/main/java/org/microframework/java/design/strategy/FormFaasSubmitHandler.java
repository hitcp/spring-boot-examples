package org.microframework.java.design.strategy;

/**
 * 绑定 FaaS 函数的提交
 */
@Component
public class FormFaasSubmitHandler implements FormSubmitHandler<Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "faas"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("FaaS 模式的提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 进行 FaaS 函数调用，并获得业务方返回的提示信息和业务数据
        CommonPairResponse<String, Serializable> response = faasSubmitData(request);

        return response;
    }

    ...
}