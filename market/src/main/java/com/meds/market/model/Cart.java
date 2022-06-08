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

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "cart", orphanRemoval = true)
    private Product product;

    @Column(name = "amount")
    private int amount;

    public Cart(int amount) {
        this.amount = amount;
        this.product = new Product();
    }

    
}
