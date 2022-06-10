package com.meds.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meds.market.model.Person;

public interface PersonRepository<T extends Person> extends JpaRepository<T, Integer> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
    Person findByPhone(int phone);
    Person findById(int id);
}
