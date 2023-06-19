package org.microframework.java.design.strategy;

import java.io.Serializable;

/**
 * HSF 模式的提交
 */
@Component
public class FormHsfSubmitHandler implements FormSubmitHandler<Serializable> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() { return "hsf"; }

    @Override
    public CommonPairResponse<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("HSF 模式提交：userId={}, formInput={}", request.getUserId(), request.getFormInput());

        // 进行 HSF 泛化调用，获得业务方返回的提示信息和业务数据
        CommonPairResponse<String, Serializable> response = hsfSubmitData(request);

        return response;
    }

    ...
}
