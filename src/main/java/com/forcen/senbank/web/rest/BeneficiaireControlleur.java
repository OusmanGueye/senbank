package com.forcen.senbank.web.rest;


import com.forcen.senbank.service.BeneficiaireService;
import com.forcen.senbank.service.dto.BeneficiaireDto;
import com.forcen.senbank.service.impl.BeneficiaireServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaire")
public class BeneficiaireControlleur {

    private final Logger log = LoggerFactory.getLogger(BeneficiaireControlleur.class);

    private final BeneficiaireService beneficiaireService;

    public BeneficiaireControlleur(BeneficiaireService beneficiaireService) {
        this.beneficiaireService = beneficiaireService;
    }

    /**
     *   Methode pour creer un beneficiaire
     *   @param beneficiaireDto
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#createBeneficiaire(BeneficiaireDto)
     **/
    @PostMapping("/create-beneficiaire")
    public ResponseEntity<BeneficiaireDto> createBeneficiaire(@RequestBody BeneficiaireDto beneficiaireDto){
        log.debug("REST request to save Beneficiaire : {}", beneficiaireDto);
        beneficiaireService.createBeneficiaire(beneficiaireDto);
        return ResponseEntity.ok().body(beneficiaireDto);
    }

    /**
     *   Methode pour recuperer un beneficiaire par son id
     *   @param id of Beneficiaire
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#getBeneficiaire(Long)
     **/
    @GetMapping("/{id}")
    public ResponseEntity<BeneficiaireDto> getBeneficiaire(@PathVariable Long id){
        log.debug("REST request to get Beneficiaire : {}", id);
        BeneficiaireDto beneficiaireDto = beneficiaireService.getBeneficiaire(id);
        return ResponseEntity.ok().body(beneficiaireDto);
    }

    /**
     *   Methode pour supprimer un beneficiaire par son id
     *   @param id of Beneficiaire
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#deleteBeneficiaire(Long)
     **/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBeneficiaire(@PathVariable Long id){
        log.debug("REST request to delete Beneficiaire : {}", id);
        beneficiaireService.deleteBeneficiaire(id);
        return ResponseEntity.ok().build();
    }

    /**
     *   Methode pour mettre a jour un beneficiaire
     *   @param beneficiaireDto
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#updateBeneficiaire(BeneficiaireDto)
     **/
    @PutMapping("/update")
    public ResponseEntity<BeneficiaireDto> updateBeneficiaire(@RequestBody BeneficiaireDto beneficiaireDto){
        log.debug("REST request to update Beneficiaire : {}", beneficiaireDto);
        beneficiaireService.updateBeneficiaire( beneficiaireDto);
        return ResponseEntity.ok().body(beneficiaireDto);
    }

    /**
     *   Methode pour recuperer tous les beneficiaires
     *   @param pageable
     *   @return List<BeneficiaireDto>
     *    @see BeneficiaireServiceImpl#getAllBeneficiaire(Pageable)
     **/
    @GetMapping("/all")
    public ResponseEntity<List<BeneficiaireDto>> getAllBeneficiaire(@ParameterObject Pageable pageable){
        log.debug("REST request to get all Beneficiaire");
        Page<BeneficiaireDto> beneficiaireDtoPage = beneficiaireService.getAllBeneficiaire(pageable);
        return ResponseEntity.ok().body(beneficiaireDtoPage.getContent());
    }

    /**
     *   Methode pour recuperer un beneficiaire par son numero de compte
     *   @param numeroCompte
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#getBeneficiaireByNumeroCompte(String)
     **/
    @GetMapping("/compte/{numeroCompte}")
    public ResponseEntity<BeneficiaireDto> getBeneficiaireByCompte(@PathVariable String numeroCompte){
        log.debug("REST request to get Beneficiaire by compte");
        BeneficiaireDto beneficiaireDto = beneficiaireService.getBeneficiaireByNumeroCompte(numeroCompte);
        return ResponseEntity.ok().body(beneficiaireDto);
    }

    /**
     *   Methode pour recuperer un beneficiaire par son numero de compte
     *   @param id, pageable
     *   @return BeneficiaireDto
     *    @see BeneficiaireServiceImpl#getBeneficiaireByNumeroCompte(String)
     **/
    @GetMapping("/user/{id}")
    public ResponseEntity<List<BeneficiaireDto>> getBeneficiaireByUser(@PathVariable Long id, @ParameterObject Pageable pageable){
        log.debug("REST request to get Beneficiaire by user");
        Page<BeneficiaireDto> beneficiaireDtoPage = beneficiaireService.getAllBeneficiaireByUser(id, pageable);
        return ResponseEntity.ok().body(beneficiaireDtoPage.getContent());
    }
}
