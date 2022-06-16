package com.meds.market.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.meds.market.enums.PayTypeEnum;
import com.meds.market.enums.PurchaseStatusEnum;
import com.meds.market.model.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class PurchaseRepositoryTest extends RunTestContainer {
// public class PurchaseRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private PurchaseRepository repository;

    Client john;
    Cart cart;
    Purchase purchase;

    @BeforeEach
    void setUp() {

        john = new Client("John Doe", "johndoe", "mypassword", "john@doe.com", "john house", 912345678);
        entityManager.persistAndFlush(john);
        
        Product benuron = new Product("benuron", "descript", 2.10f, "bene");
        Product paracetemol = new Product("paracetemol", "descript", 1.99f, "mylan");
        entityManager.persistAndFlush(benuron);
        entityManager.persistAndFlush(paracetemol);

        CartStock bs = new CartStock(benuron, 1);
        CartStock ps = new CartStock(benuron, 2);
        entityManager.persistAndFlush(bs);
        entityManager.persistAndFlush(ps);

        List<CartStock> listOfProducts = new ArrayList<>(Arrays.asList(bs, ps));

        cart = new Cart(john, listOfProducts);
        entityManager.persistAndFlush(cart);

        float total_price = bs.getSpecificStockPrice() + ps.getSpecificStockPrice();

        purchase = new Purchase(john, total_price, PayTypeEnum.DEBIT_CARD);
        purchase.setStatus(PurchaseStatusEnum.PENDENT);

        entityManager.persistAndFlush(purchase);
    }

    @Test
    void whenFindByValidId_thenReturnPurchase() {
        Optional<Purchase> purchaseFound = repository.findById(purchase.getId());
       
        assertThat( purchaseFound ).isEqualTo( Optional.of(purchase) );
        assertThat( purchaseFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Optional<Purchase> purchaseFound = repository.findById(invalidId);

        assertThat( purchaseFound ).isEmpty();
    }

    @Test
    void whenFindPurchaseByClientId_thenReturnPurchase() {
        Optional<Purchase> purchaseFound = repository.findByClientId(john.getId());

        assertThat( purchaseFound ).isEqualTo( Optional.of(purchase) );
        assertThat( purchaseFound ).isNotNull();
        assertThat(purchaseFound.get().getClient()).isEqualTo( john );
    }

    @Test
    void whenFindByInvalidClientId_thenReturnNull() {
        int invalidId = -1;
        Optional<Purchase> purchaseFound = repository.findByClientId(invalidId);

        assertThat( purchaseFound ).isEmpty();
    }

    @Test
    void whenFindPurchaseByStatus_thenReturnPurchase() {
        Optional<Purchase> purchaseFound = repository.findByStatus(PurchaseStatusEnum.DELIVERED);

        assertThat( purchaseFound ).isEmpty();
    }
    
}
