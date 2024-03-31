package com.forcen.senbank.web.rest;


import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.service.CompteService;
import com.forcen.senbank.service.dto.CompteDto;
import com.forcen.senbank.service.impl.CompteServiceImpl;
import com.forcen.senbank.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final Logger log = LoggerFactory.getLogger(CompteController.class);

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     *   Methode pour creer un compte
     *   @param compteDto
     *   @return Compte
     *    @see CompteServiceImpl#createCompte(CompteDto)
    **/
    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody CompteDto compteDto){
        log.debug("REST request to save Compte : {}", compteDto);
        return ResponseEntity.ok().body(compteService.createCompte(compteDto));
    }

    /**
     *   Methode pour recuperer tous les comptes
     *   @param pageable
     *   @return List<CompteDto>
     *       @see CompteServiceImpl#getAllCompte(Pageable)
    **/
    @GetMapping
    public ResponseEntity<List<Compte>> getAllCompte(@ParameterObject Pageable pageable){
        log.debug("REST request to get all Compte");
        Page<Compte> page = compteService.getAllCompte(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     *   Methode pour recuperer un compte par son id
     *   @param id
     *   @return CompteDto
     *       @see CompteServiceImpl#getCompte(Long)
    **/
    @GetMapping("/{id}")
    public ResponseEntity<CompteDto> getCompte(@PathVariable Long id){
        log.debug("REST request to get Compte : {}", id);
        return ResponseEntity.ok().body(compteService.getCompte(id));
    }

    /**
     *   Methode pour recuperer un compte par son numero de compte
     *   @param numeroCompte
     *   @return CompteDto
     *       @see CompteServiceImpl#getCompteByNumeroCompte(String)
    **/
    @GetMapping("/numero-compte/{numeroCompte}")
    public ResponseEntity<CompteDto> getCompteByNumeroCompte(@PathVariable String numeroCompte){
        log.debug("REST request to get Compte : {}", numeroCompte);
        return ResponseEntity.ok().body(compteService.getCompteByNumeroCompte(numeroCompte));
    }

    /**
     *   Methode pour mettre a jour un compte
     *   @param compteDto
     *   @return CompteDto
     *       @see CompteServiceImpl#updateCompte(CompteDto)
    **/
    @PutMapping
    public ResponseEntity<CompteDto> updateCompte(@RequestBody CompteDto compteDto){
        log.debug("REST request to update Compte : {}", compteDto);
        return ResponseEntity.ok().body(compteService.updateCompte(compteDto));
    }

    /**
     *   Methode pour changer l'etat d'un compte
     *   @param idCompte
     *   @param etatCompte
     *   @return Compte
     *       @see CompteServiceImpl#changeEtatCompte(Long, String)
    **/
    @PutMapping("/change-etat-compte/{idCompte}/{etatCompte}")
    public ResponseEntity<Compte> changeEtatCompte(@PathVariable Long idCompte, @PathVariable String etatCompte){
        log.debug("REST request to change etat Compte : {}", idCompte);
        return ResponseEntity.ok().body(compteService.changeEtatCompte(idCompte, etatCompte));
    }

    /**
     *   Methode pour supprimer un compte
     *   @param id
     *   @return void
     *       @see CompteServiceImpl#deleteCompte(Long)
    **/
    @DeleteMapping("/delete-compte/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id){
        log.debug("REST request to delete Compte : {}", id);
        compteService.deleteCompte(id);
        return ResponseEntity.ok().build();
    }

    /**
     *   Methode pour recuperer tous les comptes d'un utilisateur
     *   @param idUser
     *   @param pageable
     *   @return List<CompteDto>
     *       @see CompteServiceImpl#getAllCompteByUser(Long, Pageable)
    **/
    @GetMapping("/all-by-user/{idUser}")
    public ResponseEntity<List<CompteDto>> getAllCompteByUser(@PathVariable Long idUser, @ParameterObject Pageable pageable){
        log.debug("REST request to get all Compte by user : {}", idUser);
        Page<CompteDto> compteDtoPage = compteService.getAllCompteByUser(idUser, pageable);
        return ResponseEntity.ok().body(compteDtoPage.getContent());
    }

    // user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Compte>> getAllCompteByUser(@PathVariable String username){
        log.debug("REST request to get all Compte by user");
        return ResponseEntity.ok().body(compteService.getAllCompteByUserId(username));
    }

    //virement
    @PostMapping("/virement")
    public ResponseEntity<Void> virement(@RequestBody CompteDto compteDto){
        log.debug("REST request to virement : {}", compteDto);
        compteService.virement(compteDto);
        return ResponseEntity.ok().build();
    }

}
