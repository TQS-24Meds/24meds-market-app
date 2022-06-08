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

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "price", nullable = false)
    private float price;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "product", orphanRemoval = true)
    private List<Tags> tags;


    public Product(String name, String description, String photo, float price, String brand) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.brand = brand;
        this.tags = new ArrayList<>();
    }

}
