package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "stall_item_id")
    private StallItem stallItem;

    private int quantity;

    @Column(name = "added_at")
    private LocalDateTime addedAt;
}
