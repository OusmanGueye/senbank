package com.forcen.senbank.service;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.service.dto.CompteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompteService {

    /**
     * Methode pour creer un compte
     * @param compteDto
     * @return CompteDto
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#createCompte(CompteDto)
     **/
    Compte createCompte(CompteDto compteDto);

    /**
     * Methode pour mettre a jour un compte
     * @param compteDto
     * @return CompteDto
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#updateCompte(CompteDto)
     **/
    CompteDto updateCompte(CompteDto compteDto);

    /**
     * Methode pour supprimer un compte
     * @param id
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#deleteCompte(Long)
     **/
    void deleteCompte(Long id);

    /**
     * Methode pour recuperer un compte par son id
     * @param id
     * @return CompteDto
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#getCompte(Long)
     **/
    CompteDto getCompte(Long id);

    /**
     * Methode pour recuperer toutes les comptes
     * @param numeroCompte
     * @return Page<CompteDto>
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#getAllCompte(Pageable)
     **/
    CompteDto getCompteByNumeroCompte(String numeroCompte);

    /**
     * Methode pour recuperer toutes les comptes
     * @param idUser, pageable
     * @return Page<CompteDto>
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#getAllCompte(Pageable)
     **/
    Page<CompteDto> getAllCompteByUser(Long idUser, Pageable pageable);

    /**
     * Methode pour recuperer toutes les comptes
     * @param pageable
     * @return Page<CompteDto>
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#getAllCompte(Pageable)
     **/
    Page<Compte> getAllCompte(Pageable pageable);

    /**
     * Methode pour recuperer toutes les comptes
     * @param idCompte, pageable
     * @return Page<CompteDto>
     * @see com.forcen.senbank.service.impl.CompteServiceImpl#getAllCompte(Pageable)
     **/
    Compte changeEtatCompte(Long idCompte, String etatCompte);

    List<Compte> getAllCompteByUserId(String username);

    void virement(CompteDto compteDto);
}
