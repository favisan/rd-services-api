package com.rd.projetointegrador.rdservicesapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPagamento {
    //GRUPO1

    private BigInteger idFormaPagamento;
    private String dsFormaPagamento;
}
