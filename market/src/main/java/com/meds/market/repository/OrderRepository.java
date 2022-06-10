package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(int id);
    Order findByClient(Client client);
}