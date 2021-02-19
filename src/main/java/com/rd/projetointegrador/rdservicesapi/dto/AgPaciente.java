package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgPaciente {

    private BigInteger idAgPaciente;
    private Paciente paciente;
    private AgendaAgPcte agenda;
    private StatusConsulta statusConsulta;

}

