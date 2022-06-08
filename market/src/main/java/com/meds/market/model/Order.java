package com.meds.market.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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

    @Column(name = "price", nullable = false)
    private float price;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "tags", nullable = false)
    private List<String> tags;


    public Order(float price, String brand, List<String> tags) {
        this.client = new Client();
        this.price = price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

}
