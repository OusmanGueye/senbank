package com.forcen.senbank.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_de_compte")
@ToString
public class TypeDeCompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @Column(nullable = false, unique = true)
    private String nom;

    private LocalDate dateCreation;

    private double tauxInteret;

    private double fraisTransaction;

    private double fraisOuverture;

    private String prefixe;

    @Column(name = "deleted")
    private boolean isDeleted;

}
