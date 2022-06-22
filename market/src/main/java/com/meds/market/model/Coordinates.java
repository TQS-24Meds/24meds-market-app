package com.meds.market.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinates")
    private int id;

    @Column(name = "lat", nullable = false)
    private Double lat;

    @Column(name = "lon", nullable = false)
    private Double lon;

    @JsonIgnore
    @OneToOne(mappedBy = "client_location")
    private Client client;

    @JsonIgnore    
    @OneToOne(mappedBy = "store_location")
    private Pharmacy store;

    @Autowired
    public Coordinates(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    public Coordinates(){}
}
