package com.forcen.senbank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_de_compte")
public class TypeDeCompte {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @Column(nullable = false, unique = true)
    private String nom;

    private Instant dateCreation;

    private double tauxInteret;

    private double fraisTransaction;

    private double fraisOuverture;

    private String prefixe;

    private boolean isDeleted;

}
