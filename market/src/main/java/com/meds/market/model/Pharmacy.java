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
@AllArgsConstructor
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

    // @Column(name = "lat", nullable = false)
    // private double lat; 

    // @Column(name = "lon", nullable = false)
    // private double lon;    

    @OneToOne
    @JoinColumn(name = "id_coordinates")
    private Coordinates store_location;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store", orphanRemoval = true)
    private List<Product> products;

    public Pharmacy(String name, String address, Coordinates store_location) {
        this.name = name;
        this.address = address;
        this.store_location = store_location;
        this.products = new ArrayList<>();
    }


}
