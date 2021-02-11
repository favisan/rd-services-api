package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
public class Loja {

    private BigInteger idLoja;
    private BigInteger idTipoLoja;
    private BigInteger cdLoja;
    private String nmLoja;
    private String nrCnpj;
    private List<Endereco> enderecos;
    private List<Contato> contatos;
}
