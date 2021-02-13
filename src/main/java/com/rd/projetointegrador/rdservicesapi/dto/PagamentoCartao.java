package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PagamentoCartao {

    private BigInteger idCartao;
    private BigInteger idAgPaciente;
    private Integer parcelas;

}
