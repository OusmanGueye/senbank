package com.forcen.senbank.repository;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@ResponseBody
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByNumero(String numero);

    Page<Transaction> findAllByCompteEmetteur(Compte compteEmetteur, Pageable pageable);

    Page<Transaction> findAllByCompteBeneficiaire(Compte compteRecepteur, Pageable pageable);

    Page<Transaction> findAllByDeletedFalse(Pageable pageable);
}
