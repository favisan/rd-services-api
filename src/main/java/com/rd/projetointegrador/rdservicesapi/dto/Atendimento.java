package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atendimento {

    private BigInteger idAtendimento;

    private Usuario paciente;

    private Usuario medico;

    private Prontuario prontuario;

    private Float vlPeso;

    private Float vlAltura;

    private String dsHabitosVicios;

    private String dsAlergiasRestricoes;

    private String dsMedicacaoUsoContinuo;

    private String dsProblemasSaude;

    private Date dtAtendimento;

}
