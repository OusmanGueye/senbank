package com.forcen.senbank.service;

import com.forcen.senbank.service.dto.TransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    /**
     * Methode pour creer une transaction
     * @param transactionDto
     * @return TransactionDto
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#createTransaction(TransactionDto)
     **/
    TransactionDto createTransaction(TransactionDto transactionDto);

    /**
     * Methode pour mettre a jour une transaction
     * @param transactionDto
     * @return TransactionDto
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#updateTransaction(TransactionDto)
     **/
    TransactionDto updateTransaction(TransactionDto transactionDto);

    /**
     * Methode pour supprimer une transaction
     * @param id
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#deleteTransaction(Long)
     **/
    void deleteTransaction(Long id);

    /**
     * Methode pour recuperer une transaction par son id
     * @param id
     * @return TransactionDto
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#getTransaction(Long)
     **/
    TransactionDto getTransaction(Long id);

    /**
     * Methode pour recuperer toutes les transactions
     * @param pageable
     * @return Page<TransactionDto>
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#getAllTransaction(Pageable)
     **/
    Page<TransactionDto> getAllTransactionByCompteEmetteur(Long idCompteEmetteur, Pageable pageable);

    /**
     * Methode pour recuperer toutes les transactions
     * @param pageable
     * @return Page<TransactionDto>
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#getAllTransaction(Pageable)
     **/
    Page<TransactionDto> getAllTransactionByCompteBeneficiaire(Long idCompteBeneficiaire, Pageable pageable);

    /**
     * Methode pour recuperer toutes les transactions
     * @param pageable
     * @return Page<TransactionDto>
     * @see com.forcen.senbank.service.impl.TransactionServiceImpl#getAllTransaction(Pageable)
     **/
    Page<TransactionDto> getAllTransaction(Pageable pageable);

}
