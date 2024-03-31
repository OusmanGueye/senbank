package com.forcen.senbank.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Statistique {
    private Long totalCompte;
    private Long totalClient;
    private Double totalSolde;
}
