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

    private Client client;

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_delivery")
    private int id;

    @Column(name = "products")
    @ElementCollection(targetClass=Product.class)
    private List<Product> products;

     
    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp; // gives us the rider's pickup time at the store?


    @Column(name = "order_id", nullable = false)
    private int order_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;

}
