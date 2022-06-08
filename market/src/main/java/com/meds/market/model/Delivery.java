package com.meds.market.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;


import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "delivery")
public class Delivery {

    private Client client;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_delivery")
    private int id;
    
    @OneToOne               // I'm not sure if this is alright, please check
    @JoinColumn(name = "products_list", nullable = false)
    private HashMap<Product, Double> product_list;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp; // gives us the rider's pickup time at the store?

    @OneToOne           // Not sure if this relation is right aswell
    @JoinColumn(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;


    public Delivery(float client_lat, float client_long, String client_addr, Date timestamp, float price, int order_id, int store_id) {
        this.products = new ArrayList<>();
        this.client_lat = client_lat;
        this.client_long = client_long;
        this.client_addr = client_addr;
        this.timestamp = timestamp;
        this.price = price;
        this.order_id = order_id;
        this.store_id = store_id;
    }


}
