package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.*;
import com.creative.exhibitionmarketplace.repository.CartRepository;
import com.creative.exhibitionmarketplace.repository.OrderItemRepository;
import com.creative.exhibitionmarketplace.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
    }

    public Order placeOrder(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Stall stall = cart.getItems()
                .get(0)
                .getStallItem()
                .getStall();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStall(stall);
        order.setStatus("PLACED");
        order.setPlacedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        order.setItems(new ArrayList<>());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem ci : cart.getItems()) {

            if (!ci.getStallItem().getStall().getId().equals(stall.getId())) {
                throw new RuntimeException("Cart contains items from multiple stalls");
            }

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setStallItem(ci.getStallItem());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getStallItem().getPrice());

            order.getItems().add(oi);

            totalAmount = totalAmount.add(
                    oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity()))
            );
        }

        order.setTotalAmount(totalAmount);
        order.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }



    public List<Order> getOrdersForUser(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
}
