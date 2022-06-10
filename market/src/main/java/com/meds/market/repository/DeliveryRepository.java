package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Delivery;
import com.meds.market.model.Order;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Order getOrder(Delivery delivey);
    Delivery findById(int id);
}
