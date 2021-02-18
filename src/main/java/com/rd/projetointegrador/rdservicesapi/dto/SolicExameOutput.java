package com.rd.projetointegrador.rdservicesapi.dto;

import com.rd.projetointegrador.rdservicesapi.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicExameOutput {

    private String nomePaciente;

    private BigInteger idProntuario;

    private OutputMedico medico;

    private List<TipoExameEntity> listaTipoExame;

}
