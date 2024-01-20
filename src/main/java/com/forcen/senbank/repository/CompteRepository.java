package com.forcen.senbank.repository;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    Page<Compte> findAllByUser(User user, Pageable pageable);

    Optional<Compte> findOneByNumero(String numeroCompte);

   Page<Compte> findAllByUserId(Long idUser, Pageable pageable);

    Optional<Compte> findByNumero(String numeroCompte);
}
