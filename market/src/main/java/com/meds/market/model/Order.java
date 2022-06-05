package com.meds.market.model;

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
    @ElementCollection(targetClass=String.class)
    private List<String> tags;



}
