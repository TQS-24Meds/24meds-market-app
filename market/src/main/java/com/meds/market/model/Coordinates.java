package com.meds.market.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_coordinates")
    private int id_coordinates;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    @JsonIgnore
    @OneToOne(mappedBy = "person_location")
    private Person person_location;

    @JsonIgnore
    @OneToOne(mappedBy = "store_location")
    private Pharmacy store_location;

    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
