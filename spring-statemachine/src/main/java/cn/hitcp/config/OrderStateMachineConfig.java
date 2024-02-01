package cn.hitcp.config;


import cn.hitcp.enums.OrderStatus;
import cn.hitcp.enums.OrderStatusChangeEvent;
import cn.hitcp.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

@Configuration
//@EnableStateMachine(name = "orderStateMachine")
@EnableStateMachineFactory(name = "orderStateMachineFactory")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    @Qualifier("entryAction")
    @Autowired
    Action<OrderStatus, OrderStatusChangeEvent> entryAction;

    @Qualifier("exitAction")
    @Autowired
    Action<OrderStatus, OrderStatusChangeEvent> exitAction;

    @Qualifier("finishAction")
    @Autowired
    Action<OrderStatus, OrderStatusChangeEvent> finishAction;

    /**
     * 订单状态机ID
     */
    public static final String orderStateMachineId = "orderStateMachineId";


    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatus.class))
                .state(OrderStatus.FINISH, entryAction, exitAction)
                .end(OrderStatus.FINISH); // TODO 统一执行事件
    }


    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions
                .withJoin()
//                .sources(Arrays.asList(OrderStatus.WAIT_PAYMENT, OrderStatus.WAIT_DELIVER, OrderStatus.WAIT_DELIVER))
//                .target(OrderStatus.FINISH)
                .and()
                .withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvent.PAYED)
                .and()
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvent.DELIVERY)
                .and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvent.RECEIVED)
                .guard(guard())
                .action(finishAction);
    }


    public Action<OrderStatus, OrderStatusChangeEvent> finishAction() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                System.out.println("finishAction execute...");
            }
        };
    }

    /**
     * action的先决条件
     *
     * @return
     */
    public Guard<OrderStatus, OrderStatusChangeEvent> guard() {
        return new Guard<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public boolean evaluate(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                return true;
            }
        };
    }

    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persist() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order order) throws Exception {
                //此处并没有进行持久化操作
                order.setStatus(context.getState());
            }

            @Override
            public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                StateMachineContext<OrderStatus, OrderStatusChangeEvent> result = new DefaultStateMachineContext<>(order.getStatus(), null, null, null, null, orderStateMachineId);
                return result;
            }
        });
    }
}
