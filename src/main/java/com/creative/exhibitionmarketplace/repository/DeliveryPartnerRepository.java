package com.creative.exhibitionmarketplace.repository;

import com.creative.exhibitionmarketplace.entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, String> {
}
