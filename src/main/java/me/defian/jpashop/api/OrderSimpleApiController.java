package me.defian.jpashop.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.defian.jpashop.domain.Address;
import me.defian.jpashop.domain.Order;
import me.defian.jpashop.domain.OrderStatus;
import me.defian.jpashop.repository.OrderRepository;
import me.defian.jpashop.repository.OrderSearch;
import me.defian.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import me.defian.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;


    @GetMapping("/api/v1/sample-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v2/sample-orders")
    public List<SimpleOrderDto> orderV2(){
        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/sample-orders")
    public List<SimpleOrderDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o)).collect(toList());
        return result;
    }

    @GetMapping("/api/v4/sample-orders")
    public List<OrderSimpleQueryDto> orderV4(){
        List<OrderSimpleQueryDto> orders = orderSimpleQueryRepository.findOrderDtos();
        return orders;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}


