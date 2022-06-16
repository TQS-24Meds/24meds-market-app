package com.meds.market.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class CartRepositoryTest extends RunTestContainer {
// public class CartRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private CartRepository repository;

    Client john;
    Cart cart;

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

        cart = new Cart(listOfProducts);
        entityManager.persistAndFlush(cart);

        assertThat(listOfProducts).hasSize(2).extracting(CartStock::getProduct).contains(bs.getProduct(), ps.getProduct());
    }

    @Test
    void whenFindByValidId_thenReturnCart() {
        Optional<Cart> cartFound = repository.findById(cart.getId());
       
        assertThat( cartFound ).isEqualTo( Optional.of(cart) );
        assertThat( cartFound ).isNotNull();
    }

    @Test
    void whenFindByInvalidId_thenReturnNull() {
        int invalidId = -1;
        Optional<Cart> cartFound = repository.findById(invalidId);

        assertThat( cartFound ).isEmpty();
    }

    @Test
    void whenRemoveStockFromCart_ThenCartIsUpdated() {
        List<CartStock> items = cart.getCartStocks();
        items.remove(items.get(0));

        assertThat( items ).hasSize(1);

    }
    @Test
    void whenDeleteAll_ThenCartEmpty() {
        repository.deleteAll();
        assertThat(repository.count()).isEqualTo(0);
    }

}
