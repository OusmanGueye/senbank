package com.forcen.senbank.web.rest;

import com.forcen.senbank.domain.Transaction;
import com.forcen.senbank.service.TransactionService;
import com.forcen.senbank.service.dto.TransactionDto;
import com.forcen.senbank.service.impl.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final Logger log = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    /**
     *   Methode pour creer une transaction
     *   @param transactionDto
     *   @return TransactionDto
     *    @see TransactionServiceImpl#createTransaction(TransactionDto)
     **/
    @PostMapping("/create-transaction")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        log.debug("REST request to save Transaction : {}", transactionDto);
        return ResponseEntity.ok().body(transactionService.createTransaction(transactionDto));
    }


    /**
     *   Methode pour recuperer une transaction par son id
     *   @param pageable
     *   @return TransactionDto
     *    @see TransactionServiceImpl#getAllTransaction(Pageable)
     **/
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction(@ParameterObject Pageable pageable){
        log.debug("REST request to get all Transaction");
        Page<Transaction> transactionPage = transactionService.getAllTransaction(pageable);
        return ResponseEntity.ok().body(transactionPage.getContent());
    }


    /**
     *   Methode pour recuperer une transaction par son id
     *   @param id
     *   @return TransactionDto
     *    @see TransactionServiceImpl#getTransaction(Long)
     **/
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id){
        log.debug("REST request to get Transaction : {}", id);
        return ResponseEntity.ok().body(transactionService.getTransaction(id));
    }


    /**
     *   Methode pour mettre a jour une transaction
     *   @param transactionDto
     *   @return TransactionDto
     *    @see TransactionServiceImpl#updateTransaction(TransactionDto)
     **/
    @PutMapping("/update-transaction")
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto){
        log.debug("REST request to update Transaction : {}", transactionDto);
        return ResponseEntity.ok().body(transactionService.updateTransaction(transactionDto));
    }


    /**
     *   Methode pour supprimer une transaction
     *   @param id
     *   @return void
     *    @see TransactionServiceImpl#deleteTransaction(Long)
     **/
    @DeleteMapping("/delete-transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        log.debug("REST request to delete Transaction : {}", id);
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }


    /**
     *   Methode pour recuperer une transaction par son compte emetteur
     *   @param idCompteEmetteur
     *   @return TransactionDto
     *    @see TransactionServiceImpl#getAllTransactionByCompteEmetteur(Long, Pageable)
     **/
    @GetMapping("/compte-emetteur/{idCompteEmetteur}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionByCompteEmetteur(@PathVariable Long idCompteEmetteur, @ParameterObject Pageable pageable){
        log.debug("REST request to get all Transaction by compte emetteur");
        Page<TransactionDto> transactionPage = transactionService.getAllTransactionByCompteEmetteur(idCompteEmetteur, pageable);
        return ResponseEntity.ok().body(transactionPage.getContent());
    }


    /**
     *   Methode pour recuperer une transaction par son compte recepteur
     *   @param idCompteBeneficiaire
     *   @return TransactionDto
     *    @see TransactionServiceImpl#getAllTransactionByCompteBeneficiaire(Long, Pageable)
     **/
    @GetMapping("/compte-beneficiaire/{idCompteBeneficiaire}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionByCompteBeneficiaire(@PathVariable Long idCompteBeneficiaire, @ParameterObject Pageable pageable){
        log.debug("REST request to get all Transaction by compte beneficiaire");
        Page<TransactionDto> transactionPage = transactionService.getAllTransactionByCompteBeneficiaire(idCompteBeneficiaire, pageable);
        return ResponseEntity.ok().body(transactionPage.getContent());
    }
}
