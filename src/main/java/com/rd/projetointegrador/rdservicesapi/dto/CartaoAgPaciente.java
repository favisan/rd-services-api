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
    private Paciente paciente;
    private String nrCartao;
    private String codSeguranca;
    private Date dtValidade;
}
