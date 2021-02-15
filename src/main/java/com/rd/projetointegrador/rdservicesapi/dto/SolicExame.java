package com.rd.projetointegrador.rdservicesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicExame {

    private BigInteger idSolicExame;
    private Prontuario prontuario;
    private InputMedico paciente;
    private InputMedico medico;
    private Date dtSolicitacao;
    private String dsIndicacaoClin;
    private List<TipoExame> exames;
}
