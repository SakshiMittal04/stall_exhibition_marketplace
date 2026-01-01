package com.creative.exhibitionmarketplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_partners")
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String phone;
    private String email;

    @Column(name = "assigned_since")
    private LocalDateTime assignedSince;

    @OneToMany(mappedBy = "deliveryPartner")
    @JsonBackReference
    private List<Order> orders;
}
