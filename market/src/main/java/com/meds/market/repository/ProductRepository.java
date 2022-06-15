package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
    Optional<Product> findById(int id);
    Optional<Product> findByName(String name);
    Optional<Product> findByBrand(String brand);
}