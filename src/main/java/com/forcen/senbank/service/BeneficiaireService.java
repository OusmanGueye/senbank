package com.forcen.senbank.service;

import com.forcen.senbank.service.dto.BeneficiaireDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeneficiaireService {

    /**
     * Save a beneficiaire.
     *
     * @param beneficiaireDto the entity to save.
     * @return the persisted entity.
     **/
    BeneficiaireDto createBeneficiaire(BeneficiaireDto beneficiaireDto);

    /**
     * Get all the beneficiaires.
     *
     * @param beneficiaireDto the entity to update partially.
     * @return the list of entities.
     **/
    BeneficiaireDto updateBeneficiaire(BeneficiaireDto beneficiaireDto);


    /**
     * Get all the beneficiaires.
     *
     * @param id the id of the entity.
     * @return the entity.
     **/
    void deleteBeneficiaire(Long id);

    /**
     * Get all the beneficiaires.
     *
     * @param id the id of the entity.
     * @return the entity.
     **/
    BeneficiaireDto getBeneficiaire(Long id);

    /**
     * Get all the beneficiaires.
     *
     * @param numeroCompte the numeroCompte of the entity.
     * @return the entity.
     **/
    BeneficiaireDto getBeneficiaireByNumeroCompte(String numeroCompte);

    /**
     * Get all the beneficiaires.
     *
     * @param idUser the idUser of the entity.
     * @return the entity.
     **/
    Page<BeneficiaireDto> getAllBeneficiaireByUser(Long idUser, Pageable pageable);

    /**
     * Get all the beneficiaires.
     *
     * @param pageable the pageable of the entity.
     * @return the entity.
     **/
    Page<BeneficiaireDto> getAllBeneficiaire(Pageable pageable);

}
