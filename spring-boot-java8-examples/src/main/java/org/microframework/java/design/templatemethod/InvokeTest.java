package org.microframework.java.design.templatemethod;

public class InvokeTest {

    public static void main(String[] args) {
        AbstractPay pay = new AliPay();  // 调用其他支付方式直接修改实现即可 new WechatPay() new UnionPay()
        pay.pay();
    }

}

