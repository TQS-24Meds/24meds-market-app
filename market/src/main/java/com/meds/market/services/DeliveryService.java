package com.meds.market.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.enums.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class DeliveryService {
    
    @Autowired private DeliveryRepository deliveryRepository;

    @Autowired private PurchaseRepository purchaseRepository;

    @Autowired private PurchaseService purchaseService;

    public Delivery registerDelivery(Delivery delivery) {

        try { 
            Purchase purchase = delivery.getClient_purchase();
            purchaseService.updateStatus(purchase);
            purchaseRepository.save(purchase);

            delivery.setClient_purchase(purchase);

            return deliveryRepository.save(delivery);
        }
        catch (Exception e) { throw new ObjectErrorException("Failed to register delivery!"); }
    }

    public Delivery getDelivery(int id) {
        Optional<Delivery> deliveryFound = deliveryRepository.findById(id);

        if (!deliveryFound.isPresent())
            throw new ObjectErrorException("Delivery not found!");

        return deliveryFound.get();
    }


    public Delivery finishDelivery(Delivery delivery) {

        try { 
            Purchase purchase = delivery.getClient_purchase();

            purchase.setStatus(PurchaseStatusEnum.DELIVERED);
            purchaseRepository.save(purchase);

            return deliveryRepository.save(delivery);
        } 
        catch (Exception e) { throw new ObjectErrorException("Failed to finish delivery!"); }
    }
}
