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
import java.util.List;
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

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus("PLACED");
        order.setPlacedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(savedOrder);
            oi.setStallItem(ci.getStallItem());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getStallItem().getPrice());

            orderItemRepository.save(oi);

            totalAmount = totalAmount.add(
                    ci.getStallItem().getPrice()
                            .multiply(BigDecimal.valueOf(ci.getQuantity()))
            );
        }

        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);

        // Clear cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    public List<Order> getOrdersForUser(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
}
