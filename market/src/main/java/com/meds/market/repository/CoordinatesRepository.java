package com.meds.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meds.market.model.Coordinates;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
}
