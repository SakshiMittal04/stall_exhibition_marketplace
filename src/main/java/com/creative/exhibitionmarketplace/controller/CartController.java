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

    @PostMapping("/{cartId}/items")
    public Cart addToCart(@PathVariable Integer cartId,
                          @RequestParam Integer itemId,
                          @RequestParam int quantity) {
        return cartService.addItemToCart(cartId, itemId, quantity);
    }
}
