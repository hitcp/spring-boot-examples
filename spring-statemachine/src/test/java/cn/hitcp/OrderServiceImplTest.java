package cn.hitcp;

import cn.hitcp.model.Order;
import cn.hitcp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@SpringBootTest(classes = StateMachineApplication.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testMultiThread() {
        Map<Integer, Order> orders = orderService.getOrders();
        orderService.creat();
        orderService.creat();

        orderService.pay(1);
        new Thread(() -> {
            orderService.deliver(1);
            orderService.receive(1);
        }).start();

        orderService.pay(2);
        orderService.deliver(2);
        orderService.receive(2);

        System.out.println(orders);
    }
}