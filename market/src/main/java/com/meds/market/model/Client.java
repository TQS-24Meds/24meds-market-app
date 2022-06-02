package com.meds.market.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "client")
public class Client extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private int id;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lon", nullable = false)
    private float lon;

    public Client(String name, String username, float lat, float lon, String password, String email, String address, int phone) {
        super(name, username, password, email, address, phone);
        this.lat = lat;
        this.lon = lon;
    }

}