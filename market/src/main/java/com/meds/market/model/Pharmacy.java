package com.meds.market.model;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "delivery")
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


}
