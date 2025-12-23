package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.Order;
import com.creative.exhibitionmarketplace.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestParam String cartId) {
        return orderService.placeOrder(cartId);
    }
}
