package com.forcen.senbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.forcen.senbank.domain.enumeration.EtatTransaction;
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
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private double montant;

    @Column(nullable = false)
    private Instant dateTransaction;

    @Column(nullable = false)
    private boolean isDeleted;

    private Instant dateCreation;

    private String motif;

    @ManyToOne
    @JoinColumn(name = "compte_emetteur_id", nullable = false)
    private Compte compteEmetteur;

    @ManyToOne
    @JoinColumn(name = "compte_beneficiaire_id", nullable = false)
    private Compte compteBeneficiaire;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatTransaction etatTransaction;

}
