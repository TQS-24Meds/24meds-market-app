package com.meds.market.model;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "client")
public class Client extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true)
    private List<Purchase> purchases;

    @OneToOne
    private Cart cart;

    public Client(String name, String username, String email, String password, String address, int phone) {
        super(name, username, email, password, address, phone);
    }

    public Client(String name, String username, float lat, float lon, String password, String email, String address, int phone, Coordinates client_location) {
        super(name, username, password, email, address, phone, client_location);
    }

}