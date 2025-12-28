package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.Cart;
import com.creative.exhibitionmarketplace.entity.CartItem;
import com.creative.exhibitionmarketplace.entity.StallItem;
import com.creative.exhibitionmarketplace.entity.User;
import com.creative.exhibitionmarketplace.repository.CartItemRepository;
import com.creative.exhibitionmarketplace.repository.CartRepository;
import com.creative.exhibitionmarketplace.repository.StallItemRepository;
import com.creative.exhibitionmarketplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final StallItemRepository stallItemRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(
            CartRepository cartRepository,
            UserRepository userRepository,
            StallItemRepository stallItemRepository,
            CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.stallItemRepository = stallItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart createCart(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    cart.setCreatedAt(LocalDateTime.now());
                    cart.setUpdatedAt(LocalDateTime.now());
                    return cartRepository.save(cart);
                });
    }

    public Cart getCart(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addItemToCart(Integer cartId, Integer itemId, int quantity) {
        Cart cart = getCart(cartId);
        StallItem item = stallItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndStallItem(cart, item)
                .orElseGet(() -> {
                    CartItem ci = new CartItem();
                    ci.setCart(cart);
                    ci.setStallItem(item);
                    ci.setQuantity(0);
                    ci.setAddedAt(LocalDateTime.now());
                    return ci;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);

        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Integer cartId, Integer itemId) {
        Cart cart = getCart(cartId);
        StallItem item = stallItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndStallItem(cart, item)
                .orElseThrow(() -> new RuntimeException("Item not in cart"));

        cartItemRepository.delete(cartItem);
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }
}
