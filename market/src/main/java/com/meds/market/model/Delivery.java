package com.meds.market.model;

import java.util.ArrayList;
import java.util.Date;
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_delivery")
    private int id;

    @Column(name = "products")
    private List<Product> products;

    @Column(name = "client_lat", nullable = false)
    private float client_lat; 

    @Column(name = "client_long", nullable = false)
    private float client_long; 

    @Column(name = "client_addr", nullable = false)
    private String client_addr;    

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp; // gives us the rider's pickup time at the store?

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_id", nullable = false)
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
