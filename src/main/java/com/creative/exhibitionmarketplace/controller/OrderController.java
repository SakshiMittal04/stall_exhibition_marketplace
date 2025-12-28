package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.Order;
import com.creative.exhibitionmarketplace.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place order from cart
    @PostMapping("/place")
    public Order placeOrder(@RequestParam Integer cartId) {
        return orderService.placeOrder(cartId);
    }

    // User order history
    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Integer userId) {
        return orderService.getOrdersForUser(userId);
    }
}
