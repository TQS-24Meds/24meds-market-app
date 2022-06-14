package com.meds.market.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "products_list", nullable = false)
    private HashMap<Product, Double> product_list;

    @Column(name = "qr_code", nullable = false) 
    private String qr_code;

    public Purchase(Client client) { 
        this.client = client;
        this.product_list = new HashMap<>();
    }

    public Purchase(Client client, float total_price, PurchaseStatusEnum status, PayTypeEnum pay_type, HashMap<Product, Double> product_list) {
        this.client = client;
        this.timestamp = new Date();
        this.total_price = total_price;
        this.status = status;
        this.pay_type = pay_type;
        this.product_list = product_list;
    }

    public Purchase(Client client, Date timestamp, float total_price, PurchaseStatusEnum status, PayTypeEnum pay_type, String qr_code, HashMap<Product, Double> product_list) {
        this.client = client;
        this.timestamp = timestamp;
        this.total_price = total_price;
        this.status = status;
        this.pay_type = pay_type;
        this.product_list = product_list;
        this.qr_code = qr_code;
    }

}