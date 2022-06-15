package com.meds.market.services;

import java.util.List;
import java.util.Optional;

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
        Purchase purchasePlaced = purchaseRepository.save(newPurchase);

        return purchasePlaced;
    }


    Purchase getPurchase(int id) {
        Optional<Purchase> purchaseFound = purchaseRepository.findById(id);

        if (!purchaseFound.isPresent())
            throw new ResourceNotFoundException("Purchase not found.");

        return purchaseFound.get();
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

        List<CartStock> productList = purchase.getClient().getCart().getCartStocks();

        for (CartStock stock : productList) {

            float product_price = stock.getSpecificStockPrice();
            totalPrice += product_price;
        }

        return totalPrice;
    }

}
