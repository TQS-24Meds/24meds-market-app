package com.meds.market.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.meds.market.enums.PurchaseStatusEnum;
import com.meds.market.enums.PayTypeEnum;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "purchase")
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_purchase")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client; 

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "total_price", nullable = false)
    private float total_price;
    
    @Column(name = "status", nullable = false)
    private PurchaseStatusEnum status;

    @Column(name = "pay_type", nullable = false)
    private PayTypeEnum pay_type;

    @Column(name = "qr_code") 
    private String qr_code;

    // @Column(name = "client_cart")
    // private Cart client_cart;

    public Purchase(Client client) { 
        this.client = client;
        //this.client_cart = client.getCart();
    }


    @Autowired
    public Purchase(Client client, float total_price, PayTypeEnum pay_type) {
        this.client = client;
        this.timestamp = new Date();
        this.total_price = total_price;
        this.status =  PurchaseStatusEnum.PENDENT;
        this.pay_type = pay_type;
        // this.client_cart = client.getCart() ;
    }

    public Purchase(Client client, float total_price, PurchaseStatusEnum status, PayTypeEnum pay_type) {
        this.client = client;
        this.timestamp = new Date();
        this.total_price = total_price;
        this.status = status;
        this.pay_type = pay_type;
        // this.client_cart = client.getCart() ;
    }

    public Purchase(Client client, Date timestamp, float total_price, PurchaseStatusEnum status, PayTypeEnum pay_type, String qr_code) {
        this.client = client;
        this.timestamp = timestamp;
        this.total_price = total_price;
        this.status = status;
        this.pay_type = pay_type;
        // this.client_cart = client.getCart() ;
        this.qr_code = qr_code;
    }

}
