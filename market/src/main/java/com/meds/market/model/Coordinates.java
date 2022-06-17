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
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    @JsonIgnore
    @OneToOne(mappedBy = "client_location")
    private Client client;

    @JsonIgnore    
    @OneToOne(mappedBy = "store_location")
    private Pharmacy store;

    @Autowired
    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
