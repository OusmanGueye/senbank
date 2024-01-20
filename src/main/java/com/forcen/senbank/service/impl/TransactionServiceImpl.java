package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Transaction;
import com.forcen.senbank.domain.enumeration.EtatTransaction;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.TransactionRepository;
import com.forcen.senbank.service.TransactionService;
import com.forcen.senbank.service.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final CompteRepository compteRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CompteRepository compteRepository) {
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {

        // on verifie si le compte emetteur existe
        Compte compteEmetteur = compteRepository.findByNumero(transactionDto.getNumeroCompteEmetteur()).orElseThrow(() -> new RuntimeException("Le compte emetteur n'existe pas"));

        // on verifie si le compte recepteur existe
        Compte compteRecepteur = compteRepository.findByNumero(transactionDto.getNumeroCompteRecepteur()).orElseThrow(() -> new RuntimeException("Le compte recepteur n'existe pas"));

        // on verifie si le compte emetteur a assez d'argent
        if (compteEmetteur.getSolde() < transactionDto.getMontant()) throw new RuntimeException("Le compte emetteur n'a pas assez d'argent");

        // on met a jour le solde du compte emetteur
        compteEmetteur.setSolde(compteEmetteur.getSolde() - transactionDto.getMontant());

        // on met a jour le solde du compte recepteur
        compteRecepteur.setSolde(compteRecepteur.getSolde() + transactionDto.getMontant());

        // on met a jour les comptes
        compteRepository.save(compteEmetteur);
        compteRepository.save(compteRecepteur);

        // on met les infos de la transaction
        Transaction transaction = transactionDto.toEntity();
        transaction.setCompteEmetteur(compteEmetteur);
        transaction.setCompteBeneficiaire(compteRecepteur);
        transaction.setDateTransaction(Instant.now());
        transaction.setDateCreation(Instant.now());
        transaction.setDeleted(false);
        transaction.setEtatTransaction(EtatTransaction.ACCEPTEE);

        // on sauvegarde la transaction
        return new TransactionDto(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDto) {

        // on verifie si la transaction existe
        Transaction transaction = transactionRepository.findById(transactionDto.getId()).orElseThrow(() -> new RuntimeException("La transaction n'existe pas"));

        // on met a jour la transaction
        transaction.setEtatTransaction(EtatTransaction.valueOf(transactionDto.getEtatTransaction()));

        // on sauvegarde la transaction
        return new TransactionDto(transactionRepository.save(transaction));
    }

    @Override
    public void deleteTransaction(Long id) {

        // on verifie si la transaction existe
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("La transaction n'existe pas"));

        // on met a jour la transaction
        transaction.setDeleted(true);

        // on sauvegarde la transaction
        transactionRepository.save(transaction);
    }

    @Override
    public TransactionDto getTransaction(Long id) {
        return transactionRepository.findById(id).map(TransactionDto::new).orElseThrow(() -> new RuntimeException("La transaction n'existe pas"));
    }

    @Override
    public Page<TransactionDto> getAllTransactionByCompteEmetteur(Long idCompteEmetteur, Pageable pageable) {
        // on verifie si le compte emetteur existe
        Compte compteEmetteur = compteRepository.findById(idCompteEmetteur).orElseThrow(() -> new RuntimeException("Le compte emetteur n'existe pas"));

        // on recupere les transactions emises
        return transactionRepository.findAllByCompteEmetteur(compteEmetteur, pageable).map(TransactionDto::new);
    }

    @Override
    public Page<TransactionDto> getAllTransactionByCompteBeneficiaire(Long idCompteBeneficiaire, Pageable pageable) {
        // on verifie si le compte recepteur existe
        Compte compteBeneficiaire = compteRepository.findById(idCompteBeneficiaire).orElseThrow(() -> new RuntimeException("Le compte recepteur n'existe pas"));

        // on recupere les transactions recues
        return transactionRepository.findAllByCompteBeneficiaire(compteBeneficiaire, pageable).map(TransactionDto::new);
    }

    @Override
    public Page<TransactionDto> getAllTransaction(Pageable pageable) {
        return transactionRepository.findAllByDeletedFalse(pageable).map(TransactionDto::new);
    }

}
