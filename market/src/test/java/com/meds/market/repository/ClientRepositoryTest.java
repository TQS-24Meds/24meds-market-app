package com.meds.market.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.meds.market.model.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ClientRepositoryTest extends RunTestContainer {
// public class ClientRepositoryTest {
 
    @Autowired private TestEntityManager entityManager;

    @Autowired private ClientRepository repository;

    Client john;

    @BeforeEach
    void setUp() {
        john = new Client("John Doe", "johndoe", "john@doe.com", "johnpass", "john house", 911111111);
        entityManager.persistAndFlush(john);
        
        Coordinates loc = new Coordinates(-39.80711, 151.71425);
        entityManager.persistAndFlush(loc);
    }

    @Test
    void whenFindClientByValidId_thenReturnValidClient() {
        Client clientFound = repository.findById(john.getId()).orElse(null);

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
        Client clientFound = repository.findByUsername(john.getUsername()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidUsername_thenReturnNull() {
        String invalidUsername = "invalid username";
        Client clientFound = repository.findByUsername(invalidUsername).orElse(null);

        assertThat( clientFound ).isNull();

    }

    @Test
    void whenFindClientByValidEmail_thenReturnValidClient() {
        Client clientFound = repository.findByEmail(john.getEmail()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidEmail_thenReturnNull() {
        String invalidEmail = "invalid@email.com";
        Client clientFound = repository.findByEmail(invalidEmail).orElse(null);

        assertThat( clientFound ).isNull();

    }

    @Test
    void whenFindClientByValidPhoneNumber_thenReturnValidClient() {
        Client clientFound = repository.findByPhone(john.getPhone()).orElse(null);

        assertThat( clientFound ).isEqualTo( john );
        assertThat( clientFound ).isNotNull();
    }
    
    @Test
    void whenFindClientByInvalidPhone_thenReturnNull() {
        int invalidPhone = 1;
        Client clientFound = repository.findByPhone(invalidPhone).orElse(null);

        assertThat( clientFound ).isNull();
    }

    @Test
    void whenFindAllClients_thenReturnAllClients() {
        Coordinates dan_coor = new Coordinates(-19.07313, -167.85151);
        Coordinates alice_coor = new Coordinates(24.00310, -111.82589);
        Coordinates alex_coor = new Coordinates( -81.30103, 1.1087723);
        entityManager.persist(dan_coor);
        entityManager.persist(alice_coor);
        entityManager.persist(alex_coor);

        Client dan = new Client("Dan Daniel", "dandainel", "dan@daniel.com", "mypassword", "dan house", 912345678);
        Client alice = new Client("Alice Wonder", "alicewonder", "alice@wonder.com", "mypassword", "alice house", 912345678);
        Client alex = new Client("Alex Brain", "alexbrain", "alex@brain.com", "mypassword", "alex house", 912345678);
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
