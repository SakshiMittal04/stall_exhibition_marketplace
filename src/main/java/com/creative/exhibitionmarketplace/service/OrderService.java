package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.Cart;
import com.creative.exhibitionmarketplace.entity.CartItem;
import com.creative.exhibitionmarketplace.entity.Order;
import com.creative.exhibitionmarketplace.entity.OrderItem;
import com.creative.exhibitionmarketplace.repository.CartRepository;
import com.creative.exhibitionmarketplace.repository.OrderItemRepository;
import com.creative.exhibitionmarketplace.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
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

        Order order = new Order();
        order.setId(Integer.valueOf(UUID.randomUUID().toString()));
        order.setUser(cart.getUser());
        order.setStatus("PLACED");
        order.setPlacedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setId(Integer.valueOf(UUID.randomUUID().toString()));
            oi.setOrder(order);
            oi.setStallItem(ci.getStallItem());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getStallItem().getPrice());

            total = total.add(
                    oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity()))
            );

            orderItemRepository.save(oi);
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
}
