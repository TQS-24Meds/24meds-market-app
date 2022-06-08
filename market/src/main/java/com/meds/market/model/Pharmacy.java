package com.meds.market.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "store")
public class Pharmacy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pharmacy")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address; 

    @Column(name = "latitude", nullable = false)
    private double latitude; 

    @Column(name = "longitude", nullable = false)
    private double longitude;    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store", orphanRemoval = true)
    private List<Product> products;


    public Pharmacy(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.products = new ArrayList<>();
    }


}
