package com.meds.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class ProductService {
    
    @Autowired private ProductRepository productRepository;
    
    List<Product> getAllProducts(){ return productRepository.findAll(); }
    
    Product getProduct(int id){
        Optional<Product> productFound = productRepository.findById(id);

        if (!productFound.isPresent())
            throw new ResourceNotFoundException("Product not found.");

        return productFound.get();
    }

}
