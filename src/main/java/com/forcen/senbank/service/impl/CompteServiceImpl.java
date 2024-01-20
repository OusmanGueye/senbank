package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.TypeDeCompte;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.domain.enumeration.EtatCompte;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.TypeDeCompteRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.service.CompteService;
import com.forcen.senbank.service.dto.CompteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CompteServiceImpl implements CompteService {

    private final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);

    private final CompteRepository compteRepository;

    private final UserRepository userRepository;

    private final TypeDeCompteRepository typeDeCompteRepository;

    public CompteServiceImpl(CompteRepository compteRepository, UserRepository userRepository, TypeDeCompteRepository typeDeCompteRepository) {
        this.compteRepository = compteRepository;
        this.userRepository = userRepository;
        this.typeDeCompteRepository = typeDeCompteRepository;
    }

    @Override
    public Compte createCompte(CompteDto compteDto) {

        // on recupere l'utilisateur
        User user = userRepository.findById(compteDto.getUserId()).orElseThrow();

        // on recupere le type de compte
        TypeDeCompte typeDeCompte = typeDeCompteRepository.findById(compteDto.getTypeDeCompteId()).orElseThrow();

        // on cree le compte
        Compte compte = compteDto.toEntity();

        // on affecte le type de compte
        compte.setTypeDeCompte(typeDeCompte);
        // on affecte l'utilisateur
        compte.setUser(user);

        // on genere le numero de compte en ajoutant le prefixe du type de compte
        compte.setNumero(generateNumeroCompte(typeDeCompte.getPrefixe()));

        // on met statut du compte a actif
        compte.setEtatCompte(EtatCompte.ACTIF);

        return compteRepository.save(compte);
    }

    @Override
    public CompteDto updateCompte(CompteDto compteDto) {
        // on recupere le compte
        Compte compte = compteRepository.findById(compteDto.getId()).orElseThrow();
        // on fait la mise a jour
        compte.setEtatCompte(compteDto.getEtatCompte());
        return new CompteDto(compteRepository.save(compte));
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    @Override
    public CompteDto getCompte(Long id) {
        return compteRepository.findById(id).map(CompteDto::new).orElseThrow();
    }

    @Override
    public CompteDto getCompteByNumeroCompte(String numeroCompte) {
        return compteRepository.findByNumero(numeroCompte).map(CompteDto::new).orElseThrow();
    }

    @Override
    public Page<CompteDto> getAllCompteByUser(Long idUser, Pageable pageable) {
        return compteRepository.findAllByUserId(idUser, pageable).map(CompteDto::new);
    }

    @Override
    public Page<CompteDto> getAllCompte(Pageable pageable) {
        return compteRepository.findAll(pageable).map(CompteDto::new);
    }

    @Override
    public Compte changeEtatCompte(Long idCompte, String etatCompte) {
        return compteRepository.findById(idCompte).map(compte -> {
            compte.setEtatCompte(EtatCompte.valueOf(etatCompte));
            return compteRepository.save(compte);
        }).orElseThrow();
    }


    private String generateNumeroCompte(String prefixe) {
        StringBuilder numeroCompte = new StringBuilder();
        numeroCompte.append(prefixe);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            numeroCompte.append(random.nextInt(10));
        }
        return "SN-" + numeroCompte;
    }
}
