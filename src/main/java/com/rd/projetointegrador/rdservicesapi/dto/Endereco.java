package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class Endereco {

    private BigInteger idEndereco;

    private String dsEndereco;

    private String dsComplemento;

    private String dsBairro;

    private String nrCep;

    private BigInteger idCidade;

}