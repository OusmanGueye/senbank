package com.forcen.senbank.service.dto;

import com.forcen.senbank.domain.Beneficiaire;
import com.forcen.senbank.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaireDto {

    private Long id;

    private String nom;

    private String numeroCompte;

    private Long userId;

    private User user;

    private String username;

    public BeneficiaireDto(Beneficiaire beneficiaire) {
        this.id = beneficiaire.getId();
        this.nom = beneficiaire.getNom();
        this.numeroCompte = beneficiaire.getNumeroCompte();
        this.userId = beneficiaire.getUser().getId();
        this.username = beneficiaire.getUser().getUsername();
    }

    public Beneficiaire toEntity() {
        Beneficiaire beneficiaire = new Beneficiaire();
        beneficiaire.setNom(this.nom);
        beneficiaire.setNumeroCompte(this.numeroCompte);
        return beneficiaire;
    }
}
