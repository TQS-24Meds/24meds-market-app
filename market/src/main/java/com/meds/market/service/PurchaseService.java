package com.meds.market.service;

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

    // @Autowired private CartService cartService;

    public Purchase registerPurchase(Purchase purchase) {
        
        try { 
            purchase.setTotal_price(purchase.getTotal_price());
            purchase.setStatus(PurchaseStatusEnum.PENDENT);
            purchase.setPay_type(purchase.getPay_type());

            return purchaseRepository.save(purchase); 
        } 
        catch (Exception e) { throw new ObjectErrorException("Failed to register purchase!"); }


    }

    public Purchase getPurchase(int id) {
        Optional<Purchase> purchaseFound = purchaseRepository.findById(id);

        if (!purchaseFound.isPresent())
            throw new ObjectErrorException("Purchase not found!");

        return purchaseFound.get();
    }

    public Purchase updateStatus(Purchase purchase) {

        try { 
            PurchaseStatusEnum currentStatus = purchase.getStatus();
            purchase.setStatus(PurchaseStatusEnum.getNext(currentStatus));

            return purchaseRepository.save(purchase);
        } 
        catch (Exception e) { throw new ObjectErrorException("Failed to update purchase status!"); }
    }
}
