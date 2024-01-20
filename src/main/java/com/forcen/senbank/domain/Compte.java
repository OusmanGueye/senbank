package com.forcen.senbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.forcen.senbank.domain.enumeration.EtatCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compte")
public class Compte {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String numero;

        @Column(nullable = false)
        private double solde;

        private double decovertAutorise;

        @OneToOne
        @JoinColumn(unique = true)
        private TypeDeCompte typeDeCompte;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @OneToMany(mappedBy = "compteEmetteur")
        private List<Transaction> transactionsEmises;

        @OneToMany(mappedBy = "compteBeneficiaire")
        private List<Transaction> transactionsRecues;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EtatCompte etatCompte;

        private boolean isDeleted;
}
