package com.meds.market.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cartstock")
public class CartStock {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cartstock")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product; 

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cart", nullable = false)
    private Cart cart; 

    @Column(name = "amount", nullable = false)
    private int amount;

    @Autowired
    public CartStock(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public CartStock(Product product, int amount, Cart cart) {
        this.product = product;
        this.amount = amount;
        this.cart = cart;
    }

    // public void addAmount(int amount) { this.amount += amount; }

    // public void removeAmount(int Amount) {
    //     int result = this.amount - amount;
    //     this.amount = result <= 0 ? 0 : result;
    // }

    public float getSpecificStockPrice() { return this.getProduct().getPrice() * this.getAmount(); }

}
