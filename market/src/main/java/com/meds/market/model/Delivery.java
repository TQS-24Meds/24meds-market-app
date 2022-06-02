package com.meds.market.model;

import java.sql.Timestamp;
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
    private Timestamp timestamp; // gives us the rider's pickup time at the store?

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;

}
