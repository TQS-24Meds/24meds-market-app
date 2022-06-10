package com.meds.market.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.meds.market.enums.OrderStatusEnum;
import com.meds.market.enums.PayTypeEnum;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_order")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client; 

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "price", nullable = false)
    private float price;
    
    @Column(name = "status", nullable = false)
    private OrderStatusEnum status;

    @Column(name = "pay_type", nullable = false)
    private PayTypeEnum pay_type;

    /* tamos a repetir isro no order e delivery */
    // @Column(name = "products_list", nullable = false)
    // private HashMap<Product, Double> product_list;

    @Column(name = "qr_code", nullable = false) 
    private String qr_code;

    public Order(float price, String qr_code) {
        this.client = new Client();
        this.timestamp = new Date();
        this.price = price;
        this.status = OrderStatusEnum.PENDENT;
        this.pay_type = PayTypeEnum.CREDIT_CARD;
        // this.product_list = new HashMap<>();
        this.qr_code = qr_code;
    }

    public Order(Client client, Date timestamp, float price, OrderStatusEnum status, PayTypeEnum pay_type, String qr_code) {
        this.client = client;
        this.timestamp = timestamp;
        this.price = price;
        this.status = status;
        this.pay_type = pay_type;
        // this.product_list = product_list;
        this.qr_code = qr_code;
    }

}
