package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoPlano {
    //GRUPO1

    private BigInteger idServicoPlano;
    private String dsServico;

}
