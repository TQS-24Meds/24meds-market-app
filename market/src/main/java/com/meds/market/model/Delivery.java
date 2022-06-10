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
    private Order order;

    public Delivery() {
        this.product_list = new HashMap<>();
        this.timestamp = new Date();
        this.order = new Order();
    }

    public Delivery(Client client, HashMap<Product,Double> product_list, Date timestamp, Order order) {
        this.client = client;
        this.product_list = product_list;
        this.timestamp = timestamp;
        this.order = order;
    }
}
