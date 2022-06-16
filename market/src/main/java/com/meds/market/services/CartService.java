package com.meds.market.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class CartService {
    
    @Autowired private CartRepository cartRepository;
 
    Cart getCart(int id){
        Optional<Cart> cartFound = cartRepository.findById(id);

        if (!cartFound.isPresent())
            throw new ResourceNotFoundException("Cart not found.");

        return cartFound.get();
    }

    Map<String, Object> getProductList(Cart cart) {
        
        List<CartStock> productList = cart.getCartStocks();

        Map<String, Object> map = new HashMap<>();

        for (CartStock p : productList) {

            map.put("product", p.getProduct());
            map.put("amount", p.getAmount());
        }

        return map;
    }

    Float getCartTotalPrice(Cart cart){

        float totalPrice = 0;

        List<CartStock> stock = cart.getCartStocks();

        for (CartStock cs : stock) { totalPrice += cs.getSpecificStockPrice(); }

        return totalPrice;
    }

}
