package org.microframework.java.design.templatemethod;

public class WechatPay extends AbstractPay {
    @Override
    protected void pay() {
        System.out.println("调用微信支付");
    }
}
