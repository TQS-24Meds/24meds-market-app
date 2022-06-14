package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByPhone(int phone);
    Optional<Person> findById(int id);
}