package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {

    private BigInteger idCidade;
    private BigInteger cdCidadeIbge;
    private String dsCidade;
    private Uf uf;
}
