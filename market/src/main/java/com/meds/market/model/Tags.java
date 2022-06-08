package com.meds.market.model;


import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "tags")
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tag")
    private int id;

    @Column(name = "tag", nullable = false)
    private String tag; 
}
