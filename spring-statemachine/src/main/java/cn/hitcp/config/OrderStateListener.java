package cn.hitcp.config;

import cn.hitcp.enums.OrderStatus;
import cn.hitcp.enums.OrderStatusChangeEvent;
import cn.hitcp.model.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Component;

/**
 * @see StateMachineListener  也可以通过实现此类监听
 */
@Component("orderStateListener")
@WithStateMachine(id = OrderStateMachineConfig.orderStateMachineId)
public class OrderStateListener {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEvent> message) {
        System.out.println("----------------------------");
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_DELIVER);
        System.out.println("支付 headers=" + message.getHeaders().toString() + " event=" + message.getPayload());
        System.out.println("----------------------------");
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEvent> message) {
        System.out.println("----------------------------");
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_RECEIVE);
        System.out.println("发货 headers=" + message.getHeaders().toString() + " event=" + message.getPayload());
        System.out.println("----------------------------");
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEvent> message) {
        System.out.println("----------------------------");
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.FINISH);
        System.out.println("收货 headers=" + message.getHeaders().toString() + " event=" + message.getPayload());
        System.out.println("----------------------------");
        return true;
    }
}