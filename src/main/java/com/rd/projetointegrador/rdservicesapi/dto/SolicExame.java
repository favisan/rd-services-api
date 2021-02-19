package com.rd.projetointegrador.rdservicesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Usuario paciente;
    private InputMedico medico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale="pt-BR", timezone = "Brazil/East")
    private Date dtSolicitacao;
    private String dsIndicacaoClin;
    private List<TipoExame> exames;
}
