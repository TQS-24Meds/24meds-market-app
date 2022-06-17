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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @Mock(lenient = true) private ProductRepository repository;
    
    @InjectMocks private ProductService service;

    Product benuron, brufen, paracetemol;
    
    @BeforeEach
    void setUp() {
        benuron = new Product("benuron", "benuron descrption", 2.10f, "bayern");
        brufen = new Product("brufen", "brufen descrption", 1.99f, "mylan");
        paracetemol = new Product("paracetemol", "paracetemol descrption", 0.80f, "pfizer");

        List<Product> allProducts = Arrays.asList(benuron, brufen, paracetemol);

        Mockito.when(repository.save(benuron)).thenReturn(benuron);
        Mockito.when(repository.save(brufen)).thenThrow( ObjectErrorException.class );

        Mockito.when(repository.findAll()).thenReturn(allProducts);

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(benuron));
        Mockito.when(repository.findById(-1)).thenReturn(Optional.empty());
    }

    @Test
    void whenRegisterNewProduct_thenReturnCreatedProduct() {

        Product registeredProduct = service.registerProduct(benuron);
        assertThat( registeredProduct ).isEqualTo( benuron );

		Assertions.assertNotNull(registeredProduct);
		Assertions.assertNotNull(registeredProduct.getId());
		Assertions.assertEquals(registeredProduct.getName(), "benuron");
        
    }

    @Test
    void whenRegisterInvalidProduct_thenThrowException() {
        ObjectErrorException thrownException = assertThrows(ObjectErrorException.class, () -> service.registerProduct(brufen));

        assertThat(thrownException.getMessage()).isEqualTo("Failed to register product!");
    }


    @Test
    void whenGetProductByValidId_thenReturnValidProduct() {
        int validId = 1; // benuron id
        Product productFound = service.getProduct(validId);

        assertThat(productFound).isEqualTo(benuron);

        verifyFindByIdIsCalledOnce(validId);

    }

    @Test
    void whenGetProductByInvalidId_thenReturnException() {
        int invalidId = -1; // id of no product
        ResourceNotFoundException thrownException = assertThrows(ResourceNotFoundException.class, () -> service.getProduct(invalidId));
        
        assertThat(thrownException.getMessage()).isEqualTo("Product not found!");

        verifyFindByIdIsCalledOnce(invalidId);
    }

    @Test
    void whenGetAllProducts_thenReturnList() {
        List<Product> productsFound = service.getAllProducts();

        assertThat(productsFound).isNotNull().hasSize(3).extracting(Product::getId).contains(benuron.getId(), brufen.getId(), paracetemol.getId());
        verifyFindAllProductsIsCalledOnce();

    }

    void verifyFindAllProductsIsCalledOnce() { Mockito.verify(repository, VerificationModeFactory.times(1)).findAll(); }

    void verifyFindByIdIsCalledOnce(int id_product) { Mockito.verify(repository, VerificationModeFactory.times(1)).findById(id_product); }

}
