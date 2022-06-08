package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.Pharmacy;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    Pharmacy findById(int id);
    Pharmacy findByName(String name); //
}
