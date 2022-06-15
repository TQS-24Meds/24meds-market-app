package com.meds.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.enums.PurchaseStatusEnum;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class DeliveryService {
    
    @Autowired private DeliveryRepository deliveryRepository;

    @Autowired private PurchaseService purchaseService;

    Delivery placeDelivery(Delivery delivery) {

        Purchase purchase = delivery.getClient_purchase();
        Delivery newDelivery = new Delivery(purchase);

        purchaseService.updateStatus(purchase, PurchaseStatusEnum.ACCEPTED);

        newDelivery.setClient_purchase(purchase);
        Delivery deliveryPlaced = deliveryRepository.save(newDelivery);

        return deliveryPlaced;
    }

}
