package com.forcen.senbank.service.dto;

import com.forcen.senbank.domain.TypeDeCompte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDeCompteDto {

    private Long id;

    private String numero;

    private String nom;

    private double tauxInteret;

    private double fraisTransaction;

    private double fraisOuverture;

    private String prefixe;


    public TypeDeCompte toEntity() {
        TypeDeCompte typeDeCompte = new TypeDeCompte();
        typeDeCompte.setId(this.id);
        typeDeCompte.setNumero(this.numero);
        typeDeCompte.setNom(this.nom);
        typeDeCompte.setTauxInteret(this.tauxInteret);
        typeDeCompte.setFraisTransaction(this.fraisTransaction);
        typeDeCompte.setFraisOuverture(this.fraisOuverture);
        typeDeCompte.setPrefixe(this.prefixe);
        return typeDeCompte;
    }
}
