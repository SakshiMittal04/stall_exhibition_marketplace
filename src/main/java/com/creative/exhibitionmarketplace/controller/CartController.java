package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.Cart;
import com.creative.exhibitionmarketplace.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create cart for logged-in user
    @PostMapping
    public Cart createCart(@RequestParam Integer userId) {
        return cartService.createCart(userId);
    }

    // View cart
    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Integer cartId) {
        return cartService.getCart(cartId);
    }

    // Add item to cart
    @PostMapping("/{cartId}/items")
    public Cart addToCart(
            @PathVariable Integer cartId,
            @RequestParam Integer itemId,
            @RequestParam int quantity) {
        return cartService.addItemToCart(cartId, itemId, quantity);
    }

    // Remove item from cart
    @DeleteMapping("/{cartId}/items/{itemId}")
    public Cart removeItem(
            @PathVariable Integer cartId,
            @PathVariable Integer itemId) {
        return cartService.removeItemFromCart(cartId, itemId);
    }
}
