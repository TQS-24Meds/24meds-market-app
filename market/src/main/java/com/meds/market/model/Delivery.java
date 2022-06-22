package com.meds.market.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name = "delivery")
public class Delivery implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    
    @Column(name = "id_delivery")
    private int id;
    
    @CreationTimestamp
    @NotBlank(message = "ts is mandatory")
    @Column(name = "timestamp")
    private Date timestamp; 

    @OneToOne   
    @NotBlank(message = "id is mandatory")
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase client_purchase;

    @Column(name = "storeuid", nullable = false) 
    private int storeuid; 

    public Delivery(Purchase client_purchase) {
        this.client_purchase = client_purchase;
        this.timestamp = new Date();
    }

    public Delivery(){}
}
