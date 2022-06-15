package com.meds.market.model;

import javax.persistence.*;

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
    @JoinColumn(name = "id_product")
    private Product product; 

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart; 

    @Column(name = "amount", nullable = false)
    private int amount;

    public CartStock(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    // public void addAmount(int amount) { this.amount += amount; }

    // public void removeAmount(int Amount) {
    //     int result = this.amount - amount;
    //     this.amount = result <= 0 ? 0 : result;
    // }

    public float getSpecificStockPrice() { return this.getProduct().getPrice() * this.getAmount(); }

}
