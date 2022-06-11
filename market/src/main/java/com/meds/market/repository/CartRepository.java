package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    Cart findById(int id);
}
