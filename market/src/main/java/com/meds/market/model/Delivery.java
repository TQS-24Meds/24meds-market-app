package com.meds.market.model;

import java.util.Date;

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
    
    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp; 

    @OneToOne           
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase client_purchase;

    // what
    @Column(name = "storeuid", nullable = false) 
    private int storeuid; 

    public Delivery(Purchase client_purchase) {
        this.client_purchase = client_purchase;
        this.timestamp = new Date();
    }

}
