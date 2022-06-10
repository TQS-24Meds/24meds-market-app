package com.meds.market.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
// @NoArgsConstructor
@ToString
@Table(name = "client")
public class Client extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private int id_client;

    @ManyToMany
    @JoinTable(name = "client_cart", joinColumns = @JoinColumn(name = "id_client"),
    inverseJoinColumns = @JoinColumn(name = "id_cart"))
    @JsonIgnore
    private List<Product> cart;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "client", orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders;

    // @Column(name = "lat", nullable = false)
    // private float lat;

    // @Column(name = "lon", nullable = false)
    // private float lon;

    public Client() {
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    public Client(String name, String username, String email, String password, String address, int phone) {
        super(name, username, email, password, address, phone);
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    public Client(String name, String username, float lat, float lon, String password, String email, String address, int phone, Coordinates client_location) {
        super(name, username, password, email, address, phone, client_location);
        this.cart = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

}