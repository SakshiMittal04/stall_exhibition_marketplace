package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    private Stall stall;

    @ManyToOne
    @JoinColumn(name = "delivery_partner_id")
    private DeliveryPartner deliveryPartner;

    private String status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "placed_at")
    private LocalDateTime placedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
