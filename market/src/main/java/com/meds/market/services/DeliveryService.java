package com.meds.market.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.enums.OrderStatusEnum;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class DeliveryService {
    
    @Autowired private DeliveryRepository deliveryRepository;

    @Autowired private OrderService orderService;

    Delivery placeDelivery(Delivery delivery) {

        Order order = delivery.getOrder();
        Delivery newDelivery = new Delivery(order);

        orderService.updateStatus(order, OrderStatusEnum.ACCEPTED);

        newDelivery.setProduct_list(order.getProduct_list());
        Delivery deliveryPlaced = deliveryRepository.save(newDelivery);

        return deliveryPlaced;
    }


    Map<String, Object> getProductList(Delivery delivery) {
        
        Map<Product, Double> productList = delivery.getProduct_list();

        Map<String, Object> map = new HashMap<>();

        for (Entry<Product, Double> entry : productList.entrySet()) {

            map.put("product", entry.getKey());
            map.put("amount", entry.getValue());
        }

        return map;
    }


}
