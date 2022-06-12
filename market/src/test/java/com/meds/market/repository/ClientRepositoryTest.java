package com.meds.market.repository;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.meds.market.model.*;

@DataJpaTest
public class ClientRepositoryTest {
 
    @Autowired private TestEntityManager entityManager;

    @Autowired private ClientRepository repository;

    private Client john;

    @BeforeEach
    void setUp() {
        john = new Client("John Doe", "johndoe", "mypassword", "john@doe.com", "john house", 912345678);
        entityManager.persistAndFlush(john);
        
        // Coordinates loc = new Coordinates(-39.80711f, 151.71425f);
        // entityManager.persistAndFlush(loc);
    }

    @Test
    void whenFindClientByExistingId_thenReturnValidClient() {
        Optional<Person> clientFound = repository.findById(john.getId());

        assertThat( clientFound.isPresent(), is(true) );
        assertThat( clientFound.get(), is(john) );
    }

}
