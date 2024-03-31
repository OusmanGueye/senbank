package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.User;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.TransactionRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.service.UserService;
import com.forcen.senbank.service.dto.Statistique;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CompteRepository compteRepository;

    private final TransactionRepository transactionRepository;

    public UserServiceImpl(UserRepository userRepository, CompteRepository compteRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        log.debug("REST request to get all Users");
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUser(Long id) {
        log.debug("REST request to get User : {}", id);
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User updateUser(User user) {
        log.debug("REST request to update User : {}", user.getId());
        User useSaved = userRepository.findById(user.getId()).orElseThrow();
        useSaved.setUsername(user.getUsername());
        useSaved.setEmail(user.getEmail());
        useSaved.setName(user.getName());
        return userRepository.save(useSaved);
    }

    @Override
    public Statistique getStatistiques() {
        log.debug("REST request to get Statistiques");
        Statistique statistique = new Statistique();
        statistique.setTotalCompte(compteRepository.count());
        statistique.setTotalSolde(compteRepository.calculerTotalSoldeDesComptes());
        statistique.setTotalClient(userRepository.count());
        return statistique;
    }
}
