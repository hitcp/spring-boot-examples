package org.microframework.java.design.templatemethod;

public class AliPay extends AbstractPay{
    @Override
    protected void pay() {
        System.out.println("调用支付宝支付");
    }
}
