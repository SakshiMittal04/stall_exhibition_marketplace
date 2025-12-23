package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "delivery_partners")
public class DeliveryPartner {
    @Id
    private String id;

    private String name;
    private String phone;
    private String email;

    @Column(name = "assigned_since")
    private LocalDateTime assignedSince;
}
