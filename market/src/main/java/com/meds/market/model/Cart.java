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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cart")
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "id_client")
    // private Client client; 

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "cart", orphanRemoval = true)
    private List<Product> product_list;
    
    @Column(name = "amount")
    private int amount;

    public Cart(List<Product> product_list, int amount) {
        this.product_list = product_list;
        this.amount = amount;
    }
    
}
