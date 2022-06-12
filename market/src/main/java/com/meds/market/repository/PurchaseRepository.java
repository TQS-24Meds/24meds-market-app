package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.*;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Optional<Purchase> findById(int id);
    Purchase findByClient(Client client);
}