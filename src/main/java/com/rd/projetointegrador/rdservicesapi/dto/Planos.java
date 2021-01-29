package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Planos {

    private BigInteger idPlano;
    private String nmPlano;
    private String dsPlano;
    private Double vlPlano;
    private BigInteger idServicoPlano;

}
