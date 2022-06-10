package com.meds.market.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_product")
    private int id;

    @Column(name = "name", nullable = false)
    private String name; 
    
    @ManyToOne
    @JoinColumn(name = "id_pharmacy", nullable = false)
    private Pharmacy pharmacy; 

    @ManyToMany(mappedBy = "cart")
    private Set<Client> clients; 

    @ManyToOne
    @JoinColumn(name="id_cart", nullable=false)
    private Cart cart;

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "product_price", nullable = false)
    private float product_price;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product", orphanRemoval = true)
    private List<Tags> tags;


    public Product(String name, String description, float product_price, String brand) {
        this.name = name;
        this.description = description;
        this.product_price = product_price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

    public Product(String name, String description, String photo, float product_price, String brand) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.product_price = product_price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

}
