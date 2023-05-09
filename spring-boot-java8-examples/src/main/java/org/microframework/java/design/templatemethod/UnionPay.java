package org.microframework.java.design.templatemethod;

public class UnionPay extends AbstractPay{
    @Override
    protected void pay() {
        System.out.println("调用云闪付支付");
    }
}
