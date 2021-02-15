package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioCadastro {
    //GRUPO1

    private List<Uf> ufs;
    private List<Genero> genero;
    private List<Planos> planos;
}
