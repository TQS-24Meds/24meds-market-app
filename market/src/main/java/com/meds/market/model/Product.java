package com.meds.market.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;


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
    
    @ManyToMany
    @JoinColumn(name = "product_list", nullable = false)
    private Set<Pharmacy> pharmacy; 

    @Column(name = "description")
    private String description;
    
    @Column(name = "photo")
    private String photo;

    @Column(name = "price", nullable = false)
    private float price;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tags> tags;

    // @OneToMany(mappedBy="product")
    // private List<Stock> stock;

    @Autowired
    public Product(String name, String description, float price, String brand) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

    public Product(String name, String description, String photo, float price, String brand) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

}
