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
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_product")
    private int id;

    @Column(name = "name", nullable = false)
    private String name; 
    
    @Column(name = "pharmacyid", nullable = false)
    private int pharmacyid; 

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "price", nullable = false)
    private float price;
    
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "tags", nullable = false)
    @ElementCollection(targetClass=String.class)
    private List<String> tags;

}
