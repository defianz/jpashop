package me.defian.jpashop.controller;

import lombok.RequiredArgsConstructor;
import me.defian.jpashop.domain.Member;
import me.defian.jpashop.domain.Order;
import me.defian.jpashop.domain.item.Item;
import me.defian.jpashop.repository.OrderSearch;
import me.defian.jpashop.service.ItemService;
import me.defian.jpashop.service.MemberService;
import me.defian.jpashop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping("/order")
    public String order(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String cretaeForm(@RequestParam("memberId") Long memberId,
                             @RequestParam("itemId") Long itemId,
                             @RequestParam("count") int count
                             ){
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
