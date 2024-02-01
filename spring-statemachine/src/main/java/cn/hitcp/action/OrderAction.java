package cn.hitcp.action;

import cn.hitcp.enums.OrderStatus;
import cn.hitcp.enums.OrderStatusChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 订单事件动作
 */
@Configuration
public class OrderAction {

    @Bean
    public Action<OrderStatus, OrderStatusChangeEvent> entryAction() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                System.out.println("entry action execute...");
            }
        };
    }

    @Bean
    public Action<OrderStatus, OrderStatusChangeEvent> exitAction() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                System.out.println("exit action execute...");
            }
        };
    }

    @Bean
    public Action<OrderStatus, OrderStatusChangeEvent> finishAction() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                System.out.println("finish action execute...");
            }
        };
    }
}
