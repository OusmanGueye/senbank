package com.forcen.senbank.service.dto;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Transaction;
import com.forcen.senbank.domain.TypeDeCompte;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.domain.enumeration.EtatCompte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto {

    private Long id;

    private String numeroCompte;

    private double solde;

    private Long userId;

    private Long typeDeCompteId;

    private User user;

    private TypeDeCompte typeDeCompte;

    private EtatCompte etatCompte;

    private List<Transaction> transactionsEmises;

    private List<Transaction> transactionsRecues;


    public CompteDto(Compte compte) {
        this.id = compte.getId();
        this.numeroCompte = compte.getNumero();
        this.solde = compte.getSolde();
        this.userId = compte.getUser().getId();
        this.typeDeCompteId = compte.getTypeDeCompte().getId();
        this.user = compte.getUser();
        this.typeDeCompte = compte.getTypeDeCompte();
        this.transactionsEmises = compte.getTransactionsEmises();
        this.transactionsRecues = compte.getTransactionsRecues();
    }

    public Compte toEntity() {
        Compte compte = new Compte();
        compte.setId(this.id);
        compte.setSolde(this.solde);
        return compte;
    }
}
