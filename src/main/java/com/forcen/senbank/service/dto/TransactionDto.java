package com.forcen.senbank.service.dto;

import com.forcen.senbank.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private String numeroCompteEmetteur;

    private String numeroCompteRecepteur;

    private double montant;

    private String motif;

    private String dateTransaction;

    private Long compteId;

    private CompteDto compteEmetteur;

    private CompteDto compteRecepteur;

    private Instant dateCreation;

    private String etatTransaction;

    public  TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.numeroCompteEmetteur = transaction.getCompteEmetteur().getNumero();
        this.numeroCompteRecepteur = transaction.getCompteBeneficiaire().getNumero();
        this.montant = transaction.getMontant();
        this.motif = transaction.getMotif();
        this.dateTransaction = transaction.getDateTransaction().toString();
        this.compteId = transaction.getCompteEmetteur().getId();
        this.compteEmetteur = new CompteDto(transaction.getCompteEmetteur());
        this.compteRecepteur = new CompteDto(transaction.getCompteBeneficiaire());
        this.dateCreation = transaction.getDateCreation();
        this.etatTransaction = transaction.getEtatTransaction().toString();
    }

    public Transaction toEntity() {
        Transaction transaction = new Transaction();
        transaction.setId(this.id);
        transaction.setMontant(this.montant);
        transaction.setMotif(this.motif);
        return transaction;
    }


}
