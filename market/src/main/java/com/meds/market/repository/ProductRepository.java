package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
    Product findById(int id);
    Product findByName(String name);
}
