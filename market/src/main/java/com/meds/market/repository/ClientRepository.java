package com.meds.market.repository;


import org.springframework.stereotype.Repository;

import com.meds.market.model.Client;

@Repository
public interface ClientRepository extends PersonRepository<Client> {
}
