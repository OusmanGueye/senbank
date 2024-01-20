package com.forcen.senbank.repository;

import com.forcen.senbank.domain.Beneficiaire;
import com.forcen.senbank.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaireRepository  extends JpaRepository<Beneficiaire, Long> {

    Optional<Beneficiaire> findOneByNumeroCompte(String numeroCompte);

    Optional<Beneficiaire> findOneByNumeroCompteAndUser_Id(String numeroCompte, Long idUser);

    Page<Beneficiaire> findAllByUser(User user, Pageable pageable);

}
