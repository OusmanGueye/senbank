package com.forcen.senbank.repository;

import com.forcen.senbank.domain.TypeDeCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeDeCompteRepository extends JpaRepository<TypeDeCompte, Long> {

    Optional<TypeDeCompte> findOneByNom(String nom);

    Optional<TypeDeCompte> findOneByNumero(String numero);

    TypeDeCompte findByNom(String nom);

    Page<TypeDeCompte> findAllByDeletedIsFalse(Pageable pageable);
}
