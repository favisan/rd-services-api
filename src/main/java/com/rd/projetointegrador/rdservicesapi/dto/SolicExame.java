package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicExame {

    private BigInteger idSolicExame;
    private BigInteger idProntuario;
    private BigInteger idPaciente;
    private BigInteger idMedico;
    private Date dtSolicitacao;
    private String dsIndicacaoClin;
}
