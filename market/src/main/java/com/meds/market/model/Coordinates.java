package com.meds.market.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Coordinates {

    @Id
    @GeneratedValue
    @Column(name = "id_coordinates")
    private int id;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    @JsonIgnore
    @OneToOne(mappedBy = "person_location")
    private Person person;

    @JsonIgnore    
    @OneToOne(mappedBy = "store_location")
    private Pharmacy store;

    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
