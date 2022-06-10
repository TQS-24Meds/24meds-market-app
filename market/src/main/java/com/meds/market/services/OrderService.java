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
public class OrderService {
    
    @Autowired private OrderRepository orderRepository;
    
    Order placeOrder(Order order) {
        
        Order newOrder = new Order(order.getClient());

        newOrder.setTotal_price(getTotalPrice(order));
        newOrder.setStatus(OrderStatusEnum.PENDENT);
        newOrder.setPay_type(order.getPay_type());
        newOrder.setProduct_list(order.getProduct_list());
        Order orderPlaced = orderRepository.save(newOrder);

        return orderPlaced;
    }


    Order getOrder(int id) {
        Optional<Order> orderFound = orderRepository.findById(id);

        if (!orderFound.isPresent())
            throw new ResourceNotFoundException("Order not found.");

        return orderFound.get();
    }


    Map<String, Object> getProductList(Order order) {
        
        Map<Product, Double> productList = order.getProduct_list();

        Map<String, Object> map = new HashMap<>();

        for (Entry<Product, Double> entry : productList.entrySet()) {

            map.put("product", entry.getKey());
            map.put("amount", entry.getValue());
        }

        return map;
    }


    public Order updateStatus(Order order, OrderStatusEnum status) {

        if (status == OrderStatusEnum.PENDENT) order.setStatus(OrderStatusEnum.PENDENT);
        else if (status == OrderStatusEnum.ACCEPTED) order.setStatus(OrderStatusEnum.ACCEPTED);
        else if (status == OrderStatusEnum.PICKED_UP) order.setStatus(OrderStatusEnum.PICKED_UP);
        else if (status == OrderStatusEnum.DELIVERED) order.setStatus(OrderStatusEnum.DELIVERED);

        return orderRepository.save(order);

    }


    float getTotalPrice(Order order) {

        float totalPrice = 0;

        Map<Product, Double> productList = order.getProduct_list();

        for (Entry<Product, Double> entry : productList.entrySet()) {

            float product_price = entry.getKey().getProduct_price();
            totalPrice += product_price;
        }

        return totalPrice;
    }

}
