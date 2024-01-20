package com.forcen.senbank.service;

import com.forcen.senbank.domain.TypeDeCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeDeCompteService {

    /**
     * Methode pour creer un type de compte
     * @param typeDeCompte
     * @return TypeDeCompte
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#save(TypeDeCompte)
     **/
    TypeDeCompte save(TypeDeCompte typeDeCompte);

    /**
     * Methode pour mettre a jour un type de compte
     * @param typeDeCompte
     * @return TypeDeCompte
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#update(TypeDeCompte)
     **/
    TypeDeCompte update(TypeDeCompte typeDeCompte);

    /**
     * Methode pour supprimer un type de compte
     * @param nom
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#delete(Long)
     **/
    TypeDeCompte findByNom(String nom);

    /**
     * Methode pour recuperer un type de compte par son nom
     * @param numero
     * @return TypeDeCompte
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#findByNumero(String)
     **/
    TypeDeCompte findByNumero(String numero);

    /**
     * Methode pour recuperer un type de compte par son id
     * @param id
     * @return TypeDeCompte
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#findById(Long)
     **/
    TypeDeCompte findById(Long id);

    /**
     * Methode pour supprimer un type de compte
     * @param id
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#delete(Long)
     **/
    void delete(Long id);

    /**
     * Methode pour recuperer tous les types de comptes
     * @param pageable
     * @return Page<TypeDeCompte>
     * @see com.forcen.senbank.service.impl.TypeDeCompteServiceImpl#findAll(Pageable)
     **/
    Page<TypeDeCompte> findAll(Pageable pageable);
}
