package me.defian.jpashop.repository.order.simplequery;

import me.defian.jpashop.domain.Address;
import me.defian.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address){
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}