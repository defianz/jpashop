package me.defian.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import me.defian.jpashop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private OrderStatus orderStatus;

    private String memberName;

}
