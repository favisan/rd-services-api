package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PagamentoPlano {

    private BigInteger idUsuario;
    private BigInteger idAgPaciente;

}
