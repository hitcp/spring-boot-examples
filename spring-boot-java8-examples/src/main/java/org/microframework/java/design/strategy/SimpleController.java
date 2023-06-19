package org.microframework.java.design.strategy;


@RestController
public class SimpleController {

    @Autowired
    private FormService formService;

    @PostMapping("/form/submit")
    public CommonPairResponse<String, Serializable> submitForm(@RequestParam String submitType,
                                                               @RequestParam String formInputJson) {
        JSONObject formInput = JSON.parseObject(formInputJson);

        FormSubmitRequest request = new FormSubmitRequest();
        request.setUserId(123456L);
        request.setSubmitType(submitType);
        request.setFormInput(formInput);

        return formService.submitForm(request);
    }
}