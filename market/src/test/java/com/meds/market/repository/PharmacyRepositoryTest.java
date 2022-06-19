package com.meds.market.repository;

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

public class PharmacyRepositoryTest extends RunTestContainer {
// public class PharmacyRepositoryTest {


    @Autowired private TestEntityManager entityManager;

    @Autowired private PharmacyRepository repository;

    private Pharmacy meds;

    @BeforeEach
    void setUp() {
        Coordinates loc = new Coordinates(-39.80711, 151.71425);
        entityManager.persistAndFlush(loc);

        meds = new Pharmacy("24Meds", "Rua Colorida", loc);
        entityManager.persistAndFlush(meds);
    }

    @Test
    void whenFindPharmacyByValidId_thenReturnValidPharmacy() {
        Pharmacy pharmacyFound = repository.findById(meds.getId()).orElse(null);

        assertThat( pharmacyFound ).isEqualTo( meds );
        assertThat( pharmacyFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnInvalidPharmacy() {
        Integer invalidId = -1;
        Pharmacy pharmacyFound = repository.findById(invalidId).orElse(null);

        assertThat( pharmacyFound ).isNull();
    }

}