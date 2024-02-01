package cn.hitcp.model;


import cn.hitcp.enums.OrderStatus;
import lombok.Data;

@Data
public class Order {
    private Integer id;
    private OrderStatus status;
}
