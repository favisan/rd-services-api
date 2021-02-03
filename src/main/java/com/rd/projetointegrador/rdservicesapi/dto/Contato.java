package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class Contato {

    private BigInteger idContato;
    private BigInteger idLoja;
    private String nrDDD;
    private String nrRamal;
    private String dsContato;

}
