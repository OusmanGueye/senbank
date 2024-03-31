package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.TypeDeCompte;
import com.forcen.senbank.repository.TypeDeCompteRepository;
import com.forcen.senbank.service.TypeDeCompteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

@Service
public class TypeDeCompteServiceImpl implements TypeDeCompteService {

    private final Logger log = LoggerFactory.getLogger(TypeDeCompteServiceImpl.class);

    private final TypeDeCompteRepository typeDeCompteRepository;

    public TypeDeCompteServiceImpl(TypeDeCompteRepository typeDeCompteRepository) {
        this.typeDeCompteRepository = typeDeCompteRepository;
    }

    @Override
    public TypeDeCompte save(TypeDeCompte typeDeCompte) {

        // on verifie si le type de compte existe deja
        TypeDeCompte typeDeCompteExiste = typeDeCompteRepository.findByNom(typeDeCompte.getNom());
        if (typeDeCompteExiste != null) throw new RuntimeException("Ce type de compte existe deja");

        // on genere le numero de compte en ajoutant le prefixe du type de compte
        typeDeCompte.setNumero(generateNumeroCompte(typeDeCompte.getPrefixe()));

        // on met supprime a false
        typeDeCompte.setDeleted(false);

        // on met la date de creation
        typeDeCompte.setDateCreation(LocalDate.now());

        return typeDeCompteRepository.save(typeDeCompte);
    }

    @Override
    public TypeDeCompte update(TypeDeCompte typeDeCompte) {

        // on recupere le type de compte
        TypeDeCompte typeDeCompteExiste = typeDeCompteRepository.findById(typeDeCompte.getId()).orElseThrow();

        // om met a jour le type de compte
        typeDeCompteExiste.setNom(typeDeCompte.getNom());
        typeDeCompteExiste.setPrefixe(typeDeCompte.getPrefixe());
        typeDeCompteExiste.setTauxInteret(typeDeCompte.getTauxInteret());
        typeDeCompteExiste.setFraisOuverture(typeDeCompte.getFraisOuverture());

        return typeDeCompteRepository.save(typeDeCompteExiste);
    }

    @Override
    public TypeDeCompte findByNom(String nom) {
        return typeDeCompteRepository.findOneByNom(nom).orElse(null);
    }

    @Override
    public TypeDeCompte findByNumero(String numero) {
        return typeDeCompteRepository.findOneByNumero(numero).orElseThrow();
    }

    @Override
    public TypeDeCompte findById(Long id) {
        return typeDeCompteRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        // on recupere le type de compte et on met supprime a true
        typeDeCompteRepository.findById(id).ifPresent(typeDeCompte -> {
            typeDeCompte.setDeleted(true);
            typeDeCompteRepository.save(typeDeCompte);
        });
    }

    @Override
    public Page<TypeDeCompte> findAllTypeDeCompte(Pageable pageable) {
        log.debug("Request to get all TypeDeCompte");
        return typeDeCompteRepository.findAll(pageable);
    }

    public Page<TypeDeCompte> findAll(Pageable pageable) {
        return null;
    }

//    @Override
//    public Page<TypeDeCompte> findAll(Pageable pageable) {
//        // on recupere tous les types de compte non supprime
//        return typeDeCompteRepository.findAllByDeletedIsFalse(pageable);
//    }

    private String generateNumeroCompte(String prefixe){
        String numero = prefixe;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            numero += random.nextInt(10);
        }
        return numero;
    }
}
