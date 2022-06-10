package com.meds.market.repository;

import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Delivery;
import com.meds.market.model.Order;
import com.meds.market.model.Product;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    HashMap<Product, Double> getProductList(Delivery delivery);
    Order getOrder(Delivery delivey);
    Delivery findById(int id);
}
