package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    Optional<Cart> findById(int id);
}
