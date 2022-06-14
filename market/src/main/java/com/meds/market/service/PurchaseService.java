package com.meds.market.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.enums.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class PurchaseService {
    
    @Autowired private PurchaseRepository purchaseRepository;
    
    Purchase placePurchase(Purchase purchase) {
        
        Purchase newPurchase = new Purchase(purchase.getClient());

        newPurchase.setTotal_price(getTotalPrice(purchase));
        newPurchase.setStatus(PurchaseStatusEnum.PENDENT);
        newPurchase.setPay_type(purchase.getPay_type());
        newPurchase.setProduct_list(purchase.getProduct_list());
        Purchase purchasePlaced = purchaseRepository.save(newPurchase);

        return purchasePlaced;
    }


    Purchase getPurchase(int id) {
        Optional<Purchase> purchaseFound = purchaseRepository.findById(id);

        if (!purchaseFound.isPresent())
            throw new ResourceNotFoundException("Purchase not found.");

        return purchaseFound.get();
    }


    Map<String, Object> getProductList(Purchase purchase) {
        
        Map<Product, Double> productList = purchase.getProduct_list();

        Map<String, Object> map = new HashMap<>();

        for (Entry<Product, Double> entry : productList.entrySet()) {

            map.put("product", entry.getKey());
            map.put("amount", entry.getValue());
        }

        return map;
    }


    public Purchase updateStatus(Purchase purchase, PurchaseStatusEnum status) {

        if (status == PurchaseStatusEnum.PENDENT) purchase.setStatus(PurchaseStatusEnum.PENDENT);
        else if (status == PurchaseStatusEnum.ACCEPTED) purchase.setStatus(PurchaseStatusEnum.ACCEPTED);
        else if (status == PurchaseStatusEnum.PICKED_UP) purchase.setStatus(PurchaseStatusEnum.PICKED_UP);
        else if (status == PurchaseStatusEnum.DELIVERED) purchase.setStatus(PurchaseStatusEnum.DELIVERED);

        return purchaseRepository.save(purchase);

    }


    float getTotalPrice(Purchase purchase) {

        float totalPrice = 0;

        Map<Product, Double> productList = purchase.getProduct_list();

        for (Entry<Product, Double> entry : productList.entrySet()) {

            float product_price = entry.getKey().getProduct_price();
            totalPrice += product_price;
        }

        return totalPrice;
    }

}
