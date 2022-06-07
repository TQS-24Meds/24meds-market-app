package com.meds.market.model;

import java.sql.Timestamp;
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
    private Timestamp timestamp; // gives us the rider's pickup time at the store?

    @OneToOne           // Not sure if this relation is right aswell
    @JoinColumn(name = "order_id", nullable = false)
    private int order_id;

}
