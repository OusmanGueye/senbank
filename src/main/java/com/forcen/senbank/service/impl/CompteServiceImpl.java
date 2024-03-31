package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Transaction;
import com.forcen.senbank.domain.TypeDeCompte;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.domain.enumeration.EtatCompte;
import com.forcen.senbank.domain.enumeration.EtatTransaction;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.TransactionRepository;
import com.forcen.senbank.repository.TypeDeCompteRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.service.CompteService;
import com.forcen.senbank.service.dto.CompteDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class CompteServiceImpl implements CompteService {

    private final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);

    private final CompteRepository compteRepository;

    private final UserRepository userRepository;

    private final TypeDeCompteRepository typeDeCompteRepository;

    private final TransactionRepository transactionRepository;

    public CompteServiceImpl(CompteRepository compteRepository, UserRepository userRepository, TypeDeCompteRepository typeDeCompteRepository, TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.userRepository = userRepository;
        this.typeDeCompteRepository = typeDeCompteRepository;
        this.transactionRepository = transactionRepository;
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
        compte.setDateCreated(LocalDate.now());

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
    public Page<Compte> getAllCompte(Pageable pageable) {
        log.debug("REST request to get all Comptes");
        return compteRepository.findAll(pageable);
    }

    @Override
    public Compte changeEtatCompte(Long idCompte, String etatCompte) {
        return compteRepository.findById(idCompte).map(compte -> {
            compte.setEtatCompte(EtatCompte.valueOf(etatCompte));
            return compteRepository.save(compte);
        }).orElseThrow();
    }

    @Override
    public List<Compte> getAllCompteByUserId(String username) {
        log.debug("REST request to get all Comptes by user : {}", username);
        User user = userRepository.findByUsername(username).orElseThrow();
        return compteRepository.findAllByUserId(user.getId()).stream().peek(compte -> {
            TypeDeCompte typeDeCompte = typeDeCompteRepository.findById(compte.getTypeDeCompte().getId()).orElseThrow();
            compte.setTypeDeCompte(typeDeCompte);
        }).collect(Collectors.toList());
    }

    @Override
    public void virement(CompteDto compteDto) {
        log.debug("REST request to make a virement : {}", compteDto);
        Compte compteEmetteur = compteRepository.findById(compteDto.getIdCompte()).orElseThrow();
        Compte compteRecepteur = compteRepository.findByNumero(compteDto.getNumeroCompte()).orElseThrow();

        // on debite le compte emetteur
        compteEmetteur.setSolde(compteEmetteur.getSolde() - compteDto.getMontant());
        compteRepository.save(compteEmetteur);

        // on credite le compte recepteur
        compteRecepteur.setSolde(compteRecepteur.getSolde() + compteDto.getMontant());
        compteRepository.save(compteRecepteur);

        // on enregistre la transaction
        Transaction transaction = new Transaction();
        transaction.setMontant(compteDto.getMontant());
        transaction.setCompteEmetteur(compteEmetteur);
        transaction.setCompteBeneficiaire(compteRecepteur);
        transaction.setDateTransaction(LocalDate.now());
        transaction.setEtatTransaction(EtatTransaction.TERMINEE);
        transaction.setNumero(UUID.randomUUID().toString().substring(0, 8));


        // on sauvegarde la transaction
        transactionRepository.save(transaction);
    }


    private String generateNumeroCompte(String prefixe) {
        return "SN-" + prefixe + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
