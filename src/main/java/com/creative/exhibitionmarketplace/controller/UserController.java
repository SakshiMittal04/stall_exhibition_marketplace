package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.User;
import com.creative.exhibitionmarketplace.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/users")
public class UserController {private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
