package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.Cart;
import com.creative.exhibitionmarketplace.entity.User;
import com.creative.exhibitionmarketplace.repository.CartRepository;
import com.creative.exhibitionmarketplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository,
                       CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public User createUser(User user) {
        user.setRegisteredAt(LocalDateTime.now());
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setId(Integer.valueOf(UUID.randomUUID().toString()));
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());

        cartRepository.save(cart);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
