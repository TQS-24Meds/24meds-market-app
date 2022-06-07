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
    
    @Column(name = "status_finished", nullable = false)
    private boolean status_finished;

    @Column(name = "pay_type", nullable = false)
    private String pay_type;

    @Column(name = "products_list", nullable = false) // doesn't this need to connect with cart model? and join column?
    private HashMap<Product, Double> product_list;

    /* @Column(name = "qr_code", nullable = false) // no idea what is going on here, https://medium.com/nerd-for-tech/how-to-generate-qr-code-in-java-spring-boot-134adb81f10d
    private String qr_code; */



}
