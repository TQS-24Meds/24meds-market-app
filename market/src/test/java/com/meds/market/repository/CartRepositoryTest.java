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

import static org.assertj.core.api.Assertions.assertThat;

import com.meds.market.model.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest extends RunTestContainer {
// public class CartRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private CartRepository repository;

    Client john;
    Product benuron, paracetemol;
    CartStock bs, ps;
    Cart cart;

    @BeforeEach
    void setUp() {

        john = new Client("John Doe", "johndoe", "john@doe.com", "johnpass", "john house", 911111111);
        entityManager.persistAndFlush(john);
        
        cart = new Cart(john);
        entityManager.persistAndFlush(cart);

        benuron = new Product("benuron", "...", 2.10f, "bene");
        paracetemol = new Product("paracetemol", "...", 1.99f, "mylan");
        entityManager.persistAndFlush(benuron);
        entityManager.persistAndFlush(paracetemol);

        bs = new CartStock(benuron, 1);
        ps = new CartStock(benuron, 2);
        bs.setCart(cart);
        ps.setCart(cart);
        entityManager.persistAndFlush(bs);
        entityManager.persistAndFlush(ps);

        List<CartStock> listOfProducts = new ArrayList<>(Arrays.asList(bs, ps));

        cart.setCartStocks(listOfProducts);

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

}
