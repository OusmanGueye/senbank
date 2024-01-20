package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.Beneficiaire;
import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.repository.BeneficiaireRepository;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.service.BeneficiaireService;
import com.forcen.senbank.service.dto.BeneficiaireDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaireServiceImpl implements BeneficiaireService {

    private final Logger log = LoggerFactory.getLogger(BeneficiaireServiceImpl.class);

    private final BeneficiaireRepository beneficiaireRepository;

    private final UserRepository userRepository;

    private final CompteRepository compteRepository;

    public BeneficiaireServiceImpl(BeneficiaireRepository beneficiaireRepository, UserRepository userRepository, CompteRepository compteRepository) {
        this.beneficiaireRepository = beneficiaireRepository;
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public BeneficiaireDto createBeneficiaire(BeneficiaireDto beneficiaireDto) {

        // on recupere l'utilisateur
        User user = userRepository.findById(beneficiaireDto.getUserId()).orElseThrow();

        // on cree le compte
        Compte compte = compteRepository.findByNumero(beneficiaireDto.getNumeroCompte()).orElseThrow();

        // on recupere le beneficiaire
        Beneficiaire beneficiaire = beneficiaireDto.toEntity();
        beneficiaire.setDeleted(false);
        beneficiaire.setUser(user);
        beneficiaire.setNumeroCompte(compte.getNumero());


        return new BeneficiaireDto(beneficiaireRepository.save(beneficiaire));
    }

    @Override
    public BeneficiaireDto updateBeneficiaire(BeneficiaireDto beneficiaireDto) {

        // on recupere le beneficiaire
        Beneficiaire beneficiaire = beneficiaireRepository.findById(beneficiaireDto.getId()).orElseThrow();

        // on met a jour le beneficiaire
        beneficiaire.setNom(beneficiaireDto.getNom());

        // on recupere le compte
        Compte compte = compteRepository.findByNumero(beneficiaireDto.getNumeroCompte()).orElseThrow();
        beneficiaire.setNumeroCompte(compte.getNumero());

        // on enregistre le beneficiaire
        return new BeneficiaireDto(beneficiaireRepository.save(beneficiaire));
    }

    @Override
    public void deleteBeneficiaire(Long id) {
        beneficiaireRepository.deleteById(id);
    }

    @Override
    public BeneficiaireDto getBeneficiaire(Long id) {

        // on recupere le beneficiaire
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id).orElseThrow();

        // on retourne le beneficiaire
        return new BeneficiaireDto(beneficiaire);
    }

    @Override
    public BeneficiaireDto getBeneficiaireByNumeroCompte(String numeroCompte) {

        // on recupere le beneficiaire
        Beneficiaire beneficiaire = beneficiaireRepository.findOneByNumeroCompte(numeroCompte).orElseThrow();

        // on retourne le beneficiaire
        return new BeneficiaireDto(beneficiaire);
    }

    @Override
    public Page<BeneficiaireDto> getAllBeneficiaireByUser(Long idUser, Pageable pageable) {

        // on recupere l'utilisateur
        User user = userRepository.findById(idUser).orElseThrow();

        // on retourne la liste des beneficiaires
        return beneficiaireRepository.findAllByUser(user, pageable).map(BeneficiaireDto::new);
    }

    @Override
    public Page<BeneficiaireDto> getAllBeneficiaire(Pageable pageable) {

            // on retourne la liste des beneficiaires
            return beneficiaireRepository.findAll(pageable).map(BeneficiaireDto::new);
    }
}
