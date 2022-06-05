package com.meds.market.model;


import java.util.HashMap;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cart")
    private int id;

    @Column(name = "client_id", nullable = false)
    private int client_id;

    @Column(name = "products_list", nullable = false)
    private HashMap<Product, Double> product_list;
    
}
