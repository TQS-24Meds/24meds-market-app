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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {
    
    @Mock(lenient = true) private DeliveryRepository repository;

    @Mock(lenient = true) private PurchaseRepository purchaseRepository;

    @Mock(lenient = true) private PurchaseService purchaseService;

    @InjectMocks private DeliveryService service;

    Client john;
    Product benuron, paracetemol;
    CartStock ben_prod, par_prod;
    List<CartStock> listOfProducts;
    Cart cart;
    Purchase purchase;
    Delivery validDelivery, invalidDelivery;

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

        purchase = new Purchase(john, PayTypeEnum.DEBIT_CARD);
        purchase.setTotal_price(total_price);
        purchase.setStatus(PurchaseStatusEnum.PENDENT);

        validDelivery = new Delivery(purchase);
        validDelivery.getClient_purchase().setStatus(PurchaseStatusEnum.ACCEPTED);
        validDelivery.setTimestamp(new Date());

        Mockito.when(repository.save(validDelivery)).thenReturn(validDelivery);
        Mockito.when(repository.save(invalidDelivery)).thenThrow( ObjectErrorException.class );

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(validDelivery));
        Mockito.when(repository.findById(-1)).thenReturn(null); 

    }

    @Test
    void whenRegisterNewDelivery_thenReturnCreatedDelivery() {

        Delivery registeredDelivery = service.registerDelivery(validDelivery);
        assertThat( registeredDelivery ).isEqualTo( validDelivery );

		Assertions.assertNotNull(registeredDelivery);
		Assertions.assertNotNull(registeredDelivery.getId());
		Assertions.assertEquals(registeredDelivery.getClient_purchase().getStatus(), PurchaseStatusEnum.ACCEPTED);
        
    }

    @Test
    void whenSetDeliveryFinished_ThenStatusShouldBeFinished(){
        int validId = 1; // delivery id
        Delivery deliveryFound = service.getDelivery(validId);

        Delivery finishedDelivery = service.finishDelivery(deliveryFound);

        assertThat(finishedDelivery.getClient_purchase().getStatus()).isEqualTo(PurchaseStatusEnum.DELIVERED);
    }
}
