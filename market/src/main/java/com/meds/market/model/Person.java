package com.meds.market.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
//@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String password;

    @Email
    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private int phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coordinates")
    private Coordinates person_location;

    public Person(String name, String username, String password, String email, String address, int phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public Person(String name, String username, String password, String email, String address, int phone, Coordinates person_location) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.person_location = person_location;
    }

}