package com.meds.market.model;

import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

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

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client; 

    @OneToMany(mappedBy = "cart")
    private List<CartStock> cartStocks;

    @Autowired
    public Cart(Client client, List<CartStock> cartStocks) {
        this.client = client;
        this.cartStocks = cartStocks;
    }

    public Cart(Client client) {
        this.client = client;
    }
    
    public Float getCartPrice() { 
        // HashMap<Product, Integer> pl = new HashMap<>();

        float totalPrice = 0;
        for (CartStock stock : cartStocks){ totalPrice += stock.getSpecificStockPrice(); }

        return totalPrice;
    }
}
