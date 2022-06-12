package com.meds.market.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_delivery")
    private int id;
    
    @Column(name = "products_list")
    private HashMap<Product, Double> product_list;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp; // gives us the rider's pickup time at the store?

    @OneToOne           // Not sure if this relation is right aswell
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase purchase;

    public Delivery() {
        // this.product_list = new HashMap<>();
        this.timestamp = new Date();
        this.purchase = new Purchase();
    }

    public Delivery(Purchase purchase) {
        this.purchase = purchase;
        this.timestamp = new Date();
    }

}
