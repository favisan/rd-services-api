package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioMeusDados {
    //GRUPO1

    private Usuario usuario;
    private String dsEmail;
    private List<Contato> contatos;

    private List<Uf> ufs;
    private List<Genero> genero;
    private List<Planos> planos;

    private BigInteger idPlano;
}
