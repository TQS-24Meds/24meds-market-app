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
import com.meds.market.enums.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.services.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.mockito.internal.verification.VerificationModeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {
 
    @Mock(lenient = true) private PurchaseRepository repository;

    @InjectMocks private PurchaseService service;

    Client john;
    Product benuron, paracetemol;
    CartStock ben_prod, par_prod;
    List<CartStock> listOfProducts;
    Cart cart;
    Purchase validPurchase, invalidPurchase;

    @BeforeEach
    void setUp() {

        john = new Client("John Doe", "johndoe", "john@doe.com", "johnpass", "john house", 911111111);
        john.setId(1);

        Product benuron = new Product("benuron", "...", 2.10f, "bene");
        Product paracetemol = new Product("paracetemol", "...", 1.99f, "mylan");

        ben_prod = new CartStock(benuron, 1);
        par_prod = new CartStock(paracetemol, 2);

        List<CartStock> listOfProducts = new ArrayList<>(Arrays.asList(ben_prod, par_prod));

        cart = new Cart(john, listOfProducts);

        float total_price = ben_prod.getSpecificStockPrice() + par_prod.getSpecificStockPrice();

        validPurchase = new Purchase(john, PayTypeEnum.DEBIT_CARD);
        validPurchase.setTotal_price(total_price);
        validPurchase.setStatus(PurchaseStatusEnum.PENDENT);

        Mockito.when(repository.save(validPurchase)).thenReturn(validPurchase);
        Mockito.when(repository.save(invalidPurchase)).thenThrow( ObjectErrorException.class );

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(validPurchase));
        
        Mockito.when(repository.findById(-1)).thenReturn(Optional.empty()); 

        Mockito.when(repository.findByClient(john)).thenReturn(validPurchase); 
        Mockito.when(repository.findByClientId(1)).thenReturn(Optional.of(validPurchase)); 
        Mockito.when(repository.findByClientId(-1)).thenReturn(Optional.ofNullable(null)); 

    }

    @Test
    void whenRegisterNewPurchase_thenReturnCreatedPurchase() {

        Purchase registeredPurchase = service.registerPurchase(validPurchase);
        assertThat( registeredPurchase ).isEqualTo( validPurchase );

		assertThat(registeredPurchase.getId()).isEqualTo(validPurchase.getId());
		Assertions.assertNotNull(registeredPurchase);
		Assertions.assertEquals(registeredPurchase.getStatus(), PurchaseStatusEnum.PENDENT);
        
    }
    
    @Test
    void whenRegisterInvalidPurchase_thenThrowException() {
        ObjectErrorException thrownException = assertThrows(ObjectErrorException.class, () -> service.registerPurchase(invalidPurchase));

        assertThat(thrownException.getMessage()).isEqualTo("Failed to register purchase!");
    }

    @Test
    void whenFindPurchaseByValidClient_ThenReturnClient(){
        int validId = 1; // purchase id
        Purchase purchaseFound = service.getPurchase(validId);

        assertThat(purchaseFound).isEqualTo(validPurchase);

        verifyFindByIdIsCalledOnce(validId);
    }

    @Test
    void whenFindPurchaseByInvalidClient_ThenReturnException(){
        int invalidId = -1; // id of no purchase
        ObjectErrorException thrownException = assertThrows(ObjectErrorException.class, () -> service.getPurchase(invalidId));
        
        assertThat(thrownException.getMessage()).isEqualTo("Purchase not found!");

        verifyFindByIdIsCalledOnce(invalidId);
    }

    @Test
    void whenUpdateValidPurchaseStatus_ThenStatusIsUpdated(){
        int validId = 1; // purchase id
        Purchase purchaseFound = service.getPurchase(validId);
        Purchase updatedPurchase = service.updateStatus(purchaseFound);
        
        assertThat(updatedPurchase).isEqualTo(purchaseFound);
        assertThat(updatedPurchase.getStatus()).isEqualTo(PurchaseStatusEnum.ACCEPTED);
    }

    @Test
    void whenUpdateInvalidPurchaseStatus_ThenThrowException(){
        ObjectErrorException thrownException = assertThrows(ObjectErrorException.class, () -> service.updateStatus(invalidPurchase));
        
        assertThat(thrownException.getMessage()).isEqualTo("Failed to update purchase status!");
    }


    void verifyFindByIdIsCalledOnce(int id_purchase) { Mockito.verify(repository, VerificationModeFactory.times(1)).findById(id_purchase); }


}
