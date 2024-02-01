package cn.hitcp.service;

import cn.hitcp.model.Order;

import java.util.Map;

public interface OrderService {
    /**
     * 创建订单
     *
     * @return
     */
    Order creat();

    /**
     * 支付订单
     *
     * @param id
     * @return
     */
    Order pay(int id);

    /**
     * 发货
     *
     * @param id
     * @return
     */
    Order deliver(int id);

    /**
     * 收货
     *
     * @param id
     * @return
     */
    Order receive(int id);

    /**
     * 全部订单信息
     *
     * @return
     */
    Map<Integer, Order> getOrders();

}
