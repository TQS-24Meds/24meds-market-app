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
@Table(name = "pharmacy")
public class Pharmacy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pharmacy")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address; 
    
    @ManyToMany
    @JoinTable(name = "store_products", 
        joinColumns = @JoinColumn(name = "id_pharmacy"),
        inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<Product> product_list;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "pharmacy", orphanRemoval = true)
    // @JsonIgnore
    // private List<Product> product_list;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coordinates")
    private Coordinates store_location;;

    public Pharmacy(String name, String address, Coordinates store_location) {
        this.name = name;
        this.address = address;
        this.store_location = store_location;
        this.product_list = new ArrayList<>();
    }


}
