package com.meds.market.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import com.meds.market.model.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class ClientRepositoryTest extends RunTestContainer {
// public class ClientRepositoryTest {
 
    @Autowired private TestEntityManager entityManager;

    @Autowired private ClientRepository repository;

    Client john;

    @BeforeEach
    void setUp() {
        john = new Client("John Doe", "johndoe", "mypassword", "john@doe.com", "john house", 912345678);
        entityManager.persistAndFlush(john);
        
        Coordinates loc = new Coordinates(-39.80711f, 151.71425f);
        entityManager.persistAndFlush(loc);
    }

    @Test
    void whenFindClientByValidId_thenReturnValidClient() {
        Person clientFound = repository.findById(john.getId()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();

    }

    @Test
    void whenFindClientByInvalidId_thenReturnNull() {
        Integer invalidId = -1;
        Client clientFound = repository.findById(invalidId).orElse(null);

        assertThat( clientFound ).isNull();
    }

    @Test
    void whenFindClientByValidUsername_thenReturnValidClient() {
        Person clientFound = repository.findByUsername(john.getUsername()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidUsername_thenReturnNull() {
        String invalidUsername = "invalid username";
        Person clientFound = repository.findByUsername(invalidUsername).orElse(null);

        assertThat( clientFound ).isNull();

    }

    @Test
    void whenFindClientByValidEmail_thenReturnValidClient() {
        Person clientFound = repository.findByEmail(john.getEmail()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidEmail_thenReturnNull() {
        String invalidEmail = "invalid@email.com";
        Person clientFound = repository.findByEmail(invalidEmail).orElse(null);

        assertThat( clientFound ).isNull();

    }

    @Test
    void whenFindClientByValidPhoneNumber_thenReturnValidClient() {
        Person clientFound = repository.findByPhone(john.getPhone()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidPhone_thenReturnNull() {
        int invalidPhone = 1;
        Person clientFound = repository.findByPhone(invalidPhone).orElse(null);

        assertThat( clientFound ).isNull();
    }

    @Test
    void whenFindAllClients_thenReturnAllClients() {
        Coordinates dan_coor = new Coordinates(-19.07313f, -167.85151f);
        Coordinates alice_coor = new Coordinates(24.00310f, -111.82589f);
        Coordinates alex_coor = new Coordinates( -81.30103f, 1.1087723f);
        entityManager.persist(dan_coor);
        entityManager.persist(alice_coor);
        entityManager.persist(alex_coor);

        Client dan = new Client("Dan Daniel", "dandainel", "mypassword", "dan@daniel.com", "dan daniel", 912345678);
        Client alice = new Client("Alice Wonder", "alicewonder", "mypassword", "alice@wonder.com", "alice wonder", 912345678);
        Client alex = new Client("Alex Brain", "alexbrain", "mypassword", "alex@brain.com", "alex brain", 912345678);
        entityManager.persist(dan);
        entityManager.persist(alice);
        entityManager.persist(alex);
        
        entityManager.flush();

        List<Client> allClients = repository.findAll();

        assertThat( allClients).hasSize(4).extracting(Client::getUsername).contains(dan.getUsername(), alice.getUsername(), alex.getUsername());
        assertThat( allClients ).contains(dan);
        assertThat( allClients ).contains(alice);
        assertThat( allClients ).contains(alex);
        assertThat( allClients ).contains(john);
    }    

}
