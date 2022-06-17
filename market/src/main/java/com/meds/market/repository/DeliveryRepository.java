package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Optional<Delivery> findById(int id);
}
