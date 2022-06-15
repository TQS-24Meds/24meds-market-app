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

    @OneToMany(mappedBy = "cart")
    private List<CartStock> cartStocks;

    public Cart(List<CartStock> cartStocks) {
        this.cartStocks = cartStocks;
    }
    
}
