package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Delivery findById(int id);
}
