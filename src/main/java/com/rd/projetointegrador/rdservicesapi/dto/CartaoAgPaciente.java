package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoAgPaciente {
    private BigInteger idCartao;
    private Paciente usuario;
    private String nrCartao;
    private String codSeguranca;
    private Date dtValidade;
}
