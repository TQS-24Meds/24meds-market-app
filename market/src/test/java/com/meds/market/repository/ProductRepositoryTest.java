package com.meds.market.repository;

import java.util.Optional;

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
public class ProductRepositoryTest extends RunTestContainer {
// public class ProductRepositoryTest {
    
    @Autowired private TestEntityManager entityManager;

    @Autowired private ProductRepository repository;

    private Product prod;

    @BeforeEach
    void setUp() {

        prod = new Product("benuron", "descript", "photo", 2.10f, "bene");
        entityManager.persistAndFlush(prod);
    }

    @Test
    void whenFindByValidId_thenReturnProduct() {
        Optional<Product> productFound = repository.findById(prod.getId());
       
        assertThat( productFound ).isEqualTo( Optional.of(prod) );
        assertThat( productFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Optional<Product> productFound = repository.findById(invalidId);

        assertThat( productFound ).isEmpty();
    }

    @Test
    void whenFindByValidName_thenReturnNull() {
        Optional<Product> productFound = repository.findByName(prod.getName());

        assertThat( productFound ).isEqualTo( Optional.of(prod) );
        assertThat( productFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidName_thenReturnNull() {
        String not_prod = "Not product";
        Optional<Product> productFound = repository.findByName(not_prod);

        assertThat( productFound ).isEmpty();
    }

    @Test
    void whenFindByValidBrand_thenReturnProduct() {
        Optional<Product> productFound = repository.findByBrand(prod.getBrand());

        assertThat( productFound ).isEqualTo( Optional.of(prod) );
        assertThat( productFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidBrand_thenReturnNull() {
        String not_prod = "Not brand";
        Optional<Product> productFound = repository.findByBrand(not_prod);

        assertThat( productFound ).isEmpty();

    }

}
