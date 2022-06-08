package com.meds.market.model;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Product> products;

    @Column(name = "amount")
    private int amount;

    public Cart(int amount) {
        this.amount = amount;
        this.products = new HashSet<>();
    }

    public Cart(Set<Product> products, int amount) {
        this.products = products;
        this.amount = amount;
    }
    
}
