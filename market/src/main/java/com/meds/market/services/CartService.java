package com.meds.market.services;

import java.util.ArrayList;
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
 
    public Cart getCart(int id){
        Optional<Cart> cartFound = cartRepository.findById(id);

        if (!cartFound.isPresent())
            throw new ResourceNotFoundException("Cart not found.");

        return cartFound.get();
    }

    public Cart registerCart(Cart cart) {
        Cart registeredCart;

        try { registeredCart = cartRepository.save(cart); } 
        catch (Exception e) { throw new ObjectErrorException("Failed to register cart!"); }

        return registeredCart;
    }

    // public Map<String, Object> getProductList(Cart cart) {
        
    //     List<CartStock> productList = cart.getCartStocks();

    //     Map<String, Object> map = new HashMap<>();

    //     for (CartStock p : productList) {

    //         map.put("product", p.getProduct());
    //         map.put("amount", p.getAmount());
    //     }

    //     System.out.println(map.size());

    //     return map;
    // }

    public Map<List<String>, List<Integer>> getProductList(Cart cart) {
    
        List<CartStock> productList = cart.getCartStocks();

        List<String> products = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();

        Map<List<String>,  List<Integer>> map = new HashMap<>();

        for (CartStock p : productList) {

           products.add(p.getProduct().getName());
           amounts.add(p.getAmount());
        }

        map.put(products, amounts);

        return map;
    }


    public Float getCartTotalPrice(Cart cart){

        float totalPrice = 0;

        List<CartStock> stock = cart.getCartStocks();

        for (CartStock cs : stock) { totalPrice += cs.getSpecificStockPrice(); }

        return totalPrice;
    }

}
