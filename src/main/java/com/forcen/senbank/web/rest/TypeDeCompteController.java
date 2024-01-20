package com.forcen.senbank.web.rest;

import com.forcen.senbank.domain.TypeDeCompte;
import com.forcen.senbank.service.TypeDeCompteService;
import com.forcen.senbank.service.dto.TypeDeCompteDto;
import com.forcen.senbank.service.impl.TypeDeCompteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-de-compte")
public class TypeDeCompteController {

    private final Logger log = LoggerFactory.getLogger(TypeDeCompteController.class);

    private final TypeDeCompteService typeDeCompteService;

    public TypeDeCompteController(TypeDeCompteService typeDeCompteService) {
        this.typeDeCompteService = typeDeCompteService;
    }

    /**
     *   Methode pour creer un type de compte
     *   @param typeDeCompteDto
     *   @return TypeDeCompte
     *    @see TypeDeCompteServiceImpl#save(TypeDeCompte)
     **/
    @PostMapping("/create-type-de-compte")
    public ResponseEntity<TypeDeCompte> createTypeDeCompte(@RequestBody TypeDeCompteDto typeDeCompteDto){
        log.debug("REST request to save TypeDeCompte : {}", typeDeCompteDto);
        return ResponseEntity.ok().body(typeDeCompteService.save(typeDeCompteDto.toEntity()));
    }

    /**
     *   Methode pour recuperer tous les types de compte
     *   @param pageable
     *   @return List<TypeDeCompte>
     *       @see TypeDeCompteServiceImpl#findAll(Pageable)
    **/
    @GetMapping("/all")
    public ResponseEntity<List<TypeDeCompte>> getAllTypeDeCompte(@ParameterObject Pageable pageable){
        log.debug("REST request to get all TypeDeCompte");
        Page<TypeDeCompte> typeDeComptePage = typeDeCompteService.findAll(pageable);
        return ResponseEntity.ok().body(typeDeComptePage.getContent());
    }

    /**
     *   Methode pour recuperer un type de compte par son id
     *   @param id
     *   @return TypeDeCompte
     *       @see TypeDeCompteServiceImpl#findById(Long)
    **/
    @GetMapping("/{id}")
    public ResponseEntity<TypeDeCompte> getTypeDeCompte(@PathVariable Long id){
        log.debug("REST request to get TypeDeCompte : {}", id);
        return ResponseEntity.ok().body(typeDeCompteService.findById(id));
    }

    /**
     *   Methode pour mettre a jour un type de compte
     *   @param typeDeCompteDto
     *   @return TypeDeCompte
     *       @see TypeDeCompteServiceImpl#update(TypeDeCompte)
    **/
    @PutMapping("/update-type-de-compte")
    public ResponseEntity<TypeDeCompte> updateTypeDeCompte(@RequestBody TypeDeCompteDto typeDeCompteDto){
        log.debug("REST request to update TypeDeCompte : {}", typeDeCompteDto);
        return ResponseEntity.ok().body(typeDeCompteService.update(typeDeCompteDto.toEntity()));
    }

    /**
     *   Methode pour supprimer un type de compte
     *   @param id
     *   @return void
     *       @see TypeDeCompteServiceImpl#delete(Long)
    **/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTypeDeCompte(@PathVariable Long id){
        log.debug("REST request to delete TypeDeCompte : {}", id);
        typeDeCompteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *   Methode pour recuperer un type de compte par son nom
     *   @param nom
     *   @return TypeDeCompte
     *       @see TypeDeCompteServiceImpl#findByNom(String)
    **/
    @GetMapping("/nom/{nom}")
    public ResponseEntity<TypeDeCompte> getTypeDeCompteByNom(@PathVariable String nom){
        log.debug("REST request to get TypeDeCompte : {}", nom);
        return ResponseEntity.ok().body(typeDeCompteService.findByNom(nom));
    }

    /**
     *   Methode pour recuperer un type de compte par son numero
     *   @param numero
     *   @return TypeDeCompte
     *       @see TypeDeCompteServiceImpl#findByNumero(String)
    **/
    @GetMapping("/numero/{numero}")
    public ResponseEntity<TypeDeCompte> getTypeDeCompteByNumero(@PathVariable String numero){
        log.debug("REST request to get TypeDeCompte : {}", numero);
        return ResponseEntity.ok().body(typeDeCompteService.findByNumero(numero));
    }

}
