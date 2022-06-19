package com.meds.market.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meds.market.repository.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.services.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.mockito.internal.verification.VerificationModeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    
    @Mock(lenient = true) private CartRepository repository;

    @InjectMocks private CartService service;

    Client john;
    Product benuron, brufen, paracetemol;
    CartStock ben_prod, bru_prod, par_prod;
    List<CartStock> listOfProducts;
    Cart validCart, invalidCart;

    @BeforeEach
    void setUp() {

        john = new Client("John Doe", "johndoe", "johnpass", "john@doe.com", "john house", 912345678);
        john.setClient_location( new Coordinates(-39.80711, 151.71425) );
        
        benuron = new Product("benuron", "benuron description", 2.10f, "bayern");
        brufen = new Product("brufen", "brufen description", 1.99f, "mylan");
        paracetemol = new Product("paracetemol", "description descrption", 0.80f, "pfizer");

        ben_prod = new CartStock(benuron, 1);
        bru_prod = new CartStock(brufen, 2);
        par_prod = new CartStock(paracetemol, 1);

        listOfProducts = new ArrayList<>(Arrays.asList(ben_prod, bru_prod, par_prod));
        
        validCart = new Cart(john, listOfProducts);

        Mockito.when(repository.save(validCart)).thenReturn(validCart);
        Mockito.when(repository.save(invalidCart)).thenThrow( ObjectErrorException.class );

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(validCart));
        
        Mockito.when(repository.findById(-1)).thenReturn(Optional.empty());        
    }

    @Test
    void whenRegisterNewCart_thenReturnCreatedCart() {

        Cart registeredCart = service.registerCart(validCart);
        assertThat( registeredCart ).isEqualTo( validCart );

		assertThat(registeredCart.getId()).isEqualTo(validCart.getId());
		Assertions.assertNotNull(registeredCart);
		Assertions.assertEquals(registeredCart.getCartStocks(), listOfProducts);
        
    }

    @Test
    void whenRegisterInvalidCart_thenThrowException() {
        ObjectErrorException thrownException = assertThrows(ObjectErrorException.class, () -> service.registerCart(invalidCart));

        assertThat(thrownException.getMessage()).isEqualTo("Failed to register cart!");
    }

    @Test
    void whenGetCartPrice_ReturnCorrectPrice() {
        int validId = 1; // validCart id
        Cart cartFound = service.getCart(validId);
        Float cartPrice = service.getCartTotalPrice(cartFound);

        assertThat(cartPrice).isEqualTo(validCart.getCartPrice());
        verifyFindByIdIsCalledOnce(validId);
    }

    @Test
    void whenGetAllProducts_thenReturnList() {
        Map<List<String>, List<Integer>> productsFound = service.getProductList(validCart);
        
        List<String> products = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();

        for(Entry<List<String>, List<Integer>> entry: productsFound.entrySet()) {

            products = entry.getKey();
            amounts = entry.getValue();
        }
        
        assertThat(products).hasSize(3);
        assertThat(amounts).hasSize(3);
        
        assertThat(products).contains("benuron", "brufen", "paracetemol");
        assertThat(amounts).contains(1, 2, 1);
    }

    void verifyFindByIdIsCalledOnce(int id_cart) { Mockito.verify(repository, VerificationModeFactory.times(1)).findById(id_cart); }
}
