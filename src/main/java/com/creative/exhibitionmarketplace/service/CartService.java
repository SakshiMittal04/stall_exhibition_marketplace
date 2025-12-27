package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.Cart;
import com.creative.exhibitionmarketplace.entity.CartItem;
import com.creative.exhibitionmarketplace.entity.StallItem;
import com.creative.exhibitionmarketplace.repository.CartItemRepository;
import com.creative.exhibitionmarketplace.repository.CartRepository;
import com.creative.exhibitionmarketplace.repository.StallItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final StallItemRepository stallItemRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       StallItemRepository stallItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.stallItemRepository = stallItemRepository;
    }

    public Cart addItemToCart(String cartId, Long stallItemId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        StallItem item = stallItemRepository.findById(stallItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setId(UUID.randomUUID().toString());
        cartItem.setCart(cart);
        cartItem.setStallItem(item);
        cartItem.setQuantity(quantity);
        cartItem.setAddedAt(LocalDateTime.now());

        cartItemRepository.save(cartItem);
        return cart;
    }
}
