package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String phone;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
